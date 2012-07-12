package org.om.core.impl.persistence.jcr;

import java.util.LinkedList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.exception.PersistenceLayerException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.request.Mode;
import org.om.core.api.persistence.request.PersistenceRequest;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.PersistenceResult;
import org.om.core.impl.persistence.result.ImmutableCollectionPersistenceResult;
import org.om.core.impl.persistence.result.ImmutablePersistenceResult;
import org.om.core.impl.persistence.result.MissingPersistenceResult;
import org.om.core.impl.persistence.result.NoValuePersistenceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Jakob Külzer
 * @author tome
 * 
 */
public class JcrPersistenceAdapter implements PersistenceAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JcrPersistenceAdapter.class);

	/**
	 * The node that backs all persistence operations on the enclosing entity.
	 */
	private final Node node;

	/**
	 * The entity mapping for the entity that maps 1:1 to this node
	 */
	private final EntityMapping entityMapping;

	public JcrPersistenceAdapter(EntityMapping entityMapping, Node node) {
		this.node = node;
		this.entityMapping = entityMapping;

		LOGGER.trace("New JcrPersistenceDelegate for {} with {}", node, entityMapping);
	}

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

	public CollectionResult getCollection(CollectionMapping collectionMapping) {

		try {
			final List<String> paths = new LinkedList<String>();

			final String location = collectionMapping.getLocation();

			// TODO: This is a bit of a hack right now. We'll need a better
			// representation of how to retrieve elements for collections.

			if (node.hasNode(location)) {
				final Node collectionContainer = node.getNode(location);
				LOGGER.debug("Retrieving from node {}", collectionContainer.getPath());

				for (NodeIterator ni = collectionContainer.getNodes(); ni.hasNext();) {
					final Node child = ni.nextNode();

					LOGGER.trace("Adding {} to collection.", child.getPath());

					paths.add(child.getPath());
				}
			} else if (node.hasProperty(location) && node.getProperty(location).isMultiple()) {
				final Property property = node.getProperty(location);
				LOGGER.debug("Retrieving from property {}", property.getPath());
				for (Value v : property.getValues()) {
					paths.add(v.getString());
				}
			} else {
				throw new ObjectMapperException(
						"Cannot retrieve collection. Could not find a node or multi-value property with name "
								+ collectionMapping.getLocation() + ".");
			}

			return new ImmutableCollectionPersistenceResult(paths);

		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve collection from " + collectionMapping.getLocation());
		}
	}

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

	public void delete() throws ObjectMapperException {
		// Disabled for now. Transaction semantics need to be defined.
	}

	@Override
	public PersistenceResult getProperty(PersistenceRequest request) {

		try {

			Node node = this.node;
			if (request.getMode() == Mode.Absolute)
				throw new UnsupportedOperationException("Implement absolute node retrieval!");

			if (!node.hasProperty(request.getPath()))
				return new NoValuePersistenceResult();

			final Property property = node.getProperty(request.getPath());
			if (property.isMultiple())
				throw new UnsupportedOperationException("Implement support for multiple valued properties!");

			final Class<?> expectedType = request.getExpectedType();

			Object value;

			// The following is probably not necessary, but should save us the
			// overhead of going through conversion via property editors on the
			// interceptor level.
			if (int.class == expectedType || Integer.class == expectedType || long.class == expectedType
					|| Long.class == expectedType) {
				value = property.getLong();
			} else if (float.class == expectedType || Float.class == expectedType || double.class == expectedType
					|| Double.class == expectedType) {
				value = property.getDouble();
			} else if (boolean.class == expectedType || Boolean.class == expectedType) {
				value = property.getBoolean();
			} else {
				value = property.getString();
			}

			return new ImmutablePersistenceResult(value);
		} catch (RepositoryException e) {
			throw new PersistenceLayerException("Exception while retrieving " + request, e);
		}
	}
}
