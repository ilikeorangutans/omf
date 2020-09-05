package org.om.core.impl.persistence.jcr;

import java.util.*;

import javax.jcr.*;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;

import org.om.core.api.annotation.*;
import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;
import org.om.core.impl.persistence.result.*;
import org.om.core.impl.persistence.result.map.*;
import org.slf4j.*;

/**
 * @author Jakob Külzer
 * @author tome
 */
public class JcrPersistenceAdapter implements PersistenceAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JcrPersistenceAdapter.class);
	/**
	 * The entity mapping for the entity that maps 1:1 to this node
	 */
	private final EntityMapping entityMapping;
	/**
	 * The node that backs all persistence operations on the enclosing entity.
	 */
	private final Node node;
	private final String id;

	public JcrPersistenceAdapter(EntityMapping entityMapping, Node node) {
		this.node = node;
		this.entityMapping = entityMapping;
		try {
			id = node.getPath();
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not get path from Node.");
		}
		LOGGER.trace("New JcrPersistenceDelegate for {} with {}", node, entityMapping);
	}

	@Override
	public void delete() throws ObjectMapperException {
		// Disabled for now. Transaction semantics need to be defined.
	}

	@Override
	public CollectionResult getCollection(CollectionMapping collectionMapping) {
		try {
			final List<String> paths = new LinkedList<String>();
			final String location = collectionMapping.getLocation();
			// TODO: This is a bit of a hack right now. We'll need a better
			// representation of how to retrieve elements for collections.
			if (node.hasNode(location)) {
				final Node collectionContainer = node.getNode(location);
				LOGGER.debug("Retrieving from node {}", collectionContainer.getPath());
				for (final NodeIterator ni = collectionContainer.getNodes(); ni.hasNext();) {
					final Node child = ni.nextNode();
					// TODO: This is a quick'n'dirty fix to avoid having
					// jcr:content node in node level collections. I can't think
					// of a use case where this would be required, yet I think
					// this should be configurable in some way.
					if (child.getName().equals("jcr:content")) {
						continue;
					}
					LOGGER.trace("Adding {} to collection.", child.getPath());
					paths.add(child.getPath());
				}
			} else if (node.hasProperty(location) && node.getProperty(location).isMultiple()) {
				final Property property = node.getProperty(location);
				LOGGER.debug("Retrieving from property {}", property.getPath());
				for (final Value v : property.getValues()) {
					paths.add(v.getString());
				}
			} else {
				return new MissingCollectionPersistenceResult();
			}
			return new ImmutableCollectionPersistenceResult(paths);
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve collection from " + collectionMapping.getLocation());
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public MapResult getMapResult(CollectionMapping collectionMapping) {
		LOGGER.trace("Retrieving map using {}", collectionMapping);
		Map<Object, Object> result = Collections.EMPTY_MAP;
		try {
			final Node baseNode = node.getNode(collectionMapping.getLocation());
			LOGGER.trace("Base node {}", baseNode.getPath());
			if (collectionMapping.getCollectionMode() == CollectionMode.Properties) {
				result = new LinkedHashMap<Object, Object>();
				int i = 0;
				for (final PropertyIterator pi = baseNode.getProperties(); pi.hasNext();) {
					final Property property = pi.nextProperty();
					final String propertyName = property.getName();
					if (propertyName.startsWith("jcr:")) {
						continue;
					}
					String key;
					switch (collectionMapping.getMapKeyStrategy()) {
						case Index:
							key = Integer.toString(i);
							break;
						default:
						case Name:
							key = propertyName;
							break;
					}
					final String value = property.getString();
					result.put(key, value);
					i++;
				}
			} else {
				throw new ObjectMapperException("Don't know how to deal with collection mode " + collectionMapping.getCollectionMode() + " yet.");
			}
		} catch (final PathNotFoundException e) {
			return new ExceptionThrowingMapResult();
		} catch (final RepositoryException e) {
			throw new PersistenceLayerException("Exception while retrieving " + collectionMapping, e);
		}
		return new ImmutableMapResult(result);
	}

	@Override
	public PersistenceResult getProperty(PersistenceRequest request) {
		try {
			final Node node = this.node;
			if (request.getMode() == Mode.Absolute) {
				throw new UnsupportedOperationException("Implement absolute node retrieval!");
			}
			if (!node.hasProperty(request.getPath())) {
				return new NoValuePersistenceResult();
			}
			final Property property = node.getProperty(request.getPath());
			if (property.isMultiple()) {
				throw new UnsupportedOperationException("Implement support for multiple valued properties!");
			}
			final Class<?> expectedType = request.getExpectedType();
			Object value;
			// The following is probably not necessary, but should save us the
			// overhead of going through conversion via property editors on the
			// interceptor level.
			if ((int.class == expectedType) || (Integer.class == expectedType) || (long.class == expectedType) || (Long.class == expectedType)) {
				value = property.getLong();
			} else if ((float.class == expectedType) || (Float.class == expectedType) || (double.class == expectedType) || (Double.class == expectedType)) {
				value = property.getDouble();
			} else if ((boolean.class == expectedType) || (Boolean.class == expectedType)) {
				value = property.getBoolean();
			} else {
				value = property.getString();
			}
			return new ImmutablePersistenceResult(value);
		} catch (final RepositoryException e) {
			throw new PersistenceLayerException("Exception while retrieving " + request, e);
		}
	}

	@Override
	public PersistenceResult getProperty(PropertyMapping propertyMapping) {
		// TODO: should check if the given mapping actually exists in
		// entityMapping.
		// TODO: The missing handling should be moved into the parent:
		final String propertyName = propertyMapping.getPropertyName();
		try {
			if (!node.hasProperty(propertyName)) {
				final MappedField mappedField = entityMapping.getMappedFields().getFieldByMapping(propertyMapping);
				return MissingPersistenceResult.createMissing(mappedField);
			}
			final Property property = node.getProperty(propertyName);
			if (property.isMultiple()) {
				throw new RuntimeException("Cannot handle multi-value properties yet.");
			}
			// TODO: this could be more efficient for binary types:
			return new ImmutablePersistenceResult(property.getValue().getString());
		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Exception in getProperty " + propertyName, e);
		}
	}

	@Override
	public Object resolve(String path) {
		if (path == null) {
			throw new NullPointerException("path is null");
		}
		final boolean isAbsolutePath = path.startsWith("/");
		// If the path is absolute, no need to resolve it.
		if (isAbsolutePath) {
			return path;
		}
		try {
			return node.getPath() + "/" + path;
		} catch (final RepositoryException e) {
			throw new PersistenceLayerException("Exception while resolving path.", e);
		}
	}

	@Override
	public void setProperty(PropertyMapping propertyMapping, Object object) throws ObjectMapperException {
		// Disabled as this assumes write-through semantics which doesn't allow
		// rollback.
		// final String propertyName = propertyMapping.getPropertyName();
		// try {
		// Class<?> propertyType = propertyMapping.getFieldType();
		// if (propertyType == String.class) {
		// node.setProperty(propertyName, (String) object);
		// } else if (propertyType == int.class) {
		// node.setProperty(propertyName, ((Integer) object).intValue());
		// } else if (propertyType == Integer.class) {
		// node.setProperty(propertyName, (Integer) object);
		// } else {
		// throw new ObjectMapperException("Unknown property type");
		// }
		//
		// } catch (final RepositoryException e) {
		// throw new ObjectMapperException("Exception in setProperty " +
		// propertyName, e);
		// }
	}
}
