package org.om.core.impl.persistence.jcr;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.om.core.api.exception.ObjectMapperException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.Mapping;
import org.om.core.api.mapping.PropertyMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
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
	 * The entity mapping for the entity that maps 1:1 to this node
	 */
	private final EntityMapping entityMapping;

	/**
	 * The node that backs all persistence operations on the enclosing entity.
	 */
	private final Node node;

	/**
	 * The session
	 */
	private final Session session;

	public JcrPersistenceAdapter(Session session, EntityMapping entityMapping, Node node) {
		this.node = node;
		this.session = session;
		this.entityMapping = entityMapping;

		LOGGER.trace("New JcrPersistenceDelegate for {} with {}", node, entityMapping);
	}

	public boolean canProvide(Mapping mapping) throws ObjectMapperException {
		try {
			// TODO: This method doesn't properly reflect the fact that mappings
			// might be referencing nodes instead of properties.

			// Ugly:
			if (mapping instanceof PropertyMapping) {
				PropertyMapping prop = (PropertyMapping) mapping;

				return node.hasProperty(prop.getPropertyName());
			}

			return false;

		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Exception while retrieving property " + mapping.getFieldname(), e);
		}
	}

	public void delete() throws ObjectMapperException {
		// Disabled for now. Transaction semantics need to be defined.
	}

	public Collection<?> getCollection(CollectionMapping collectionMapping) {

		try {
			final List<String> paths = new LinkedList<String>();

			final String location = collectionMapping.getLocation();
			final Node collectionContainer = node.getNode(location);

			for (NodeIterator ni = collectionContainer.getNodes(); ni.hasNext();) {
				final Node child = ni.nextNode();
				paths.add(child.getPath());
			}

			return paths;

		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve collection from " + collectionMapping.getLocation());
		}
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		// TODO: should check if the given mapping actually exists in
		// entityMapping.

		// TODO: The missing handling should be moved into the parent:
		final String propertyName = propertyMapping.getPropertyName();
		try {

			final Property property = node.getProperty(propertyName);
			if (property.isMultiple()) {
				LOGGER.warn("Cannot handle multi-value properties yet.");
				return null;
			}

			return property.getValue().getString();

		} catch (final RepositoryException e) {
			throw new ObjectMapperException("Exception in getProperty " + propertyName, e);
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
}
