package com.omf.om.core.persistence.jcr;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omf.om.api.annotation.PropertyMissingStrategy;
import com.omf.om.api.exception.ObjectMapperException;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.PropertyMapping;
import com.omf.om.api.persistence.PersistenceDelegate;
import com.omf.om.api.session.Session;

public class JcrPersistenceDelegate implements PersistenceDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(JcrPersistenceDelegate.class);

	private final EntityMapping entityMapping;
	private final Session session;

	private final Node node;

	public JcrPersistenceDelegate(Session session, EntityMapping entityMapping, Node node) {
		this.session = session;
		this.entityMapping = entityMapping;
		this.node = node;
	}

	public Object getProperty(PropertyMapping propertyMapping) {
		// TODO: should check if the given mapping actually exists in
		// entityMapping.

		// TODO: The missing handling should be moved into the parent:
		final String propertyName = propertyMapping.getPropertyName();
		try {
			if (!node.hasProperty(propertyName)) {
				final PropertyMissingStrategy strategy = propertyMapping.getMissingStrategy();
				switch (strategy) {
				case DefaultValue:
					return propertyMapping.getDefaultValue();

				case ReturnNull:
					return null;

				case ThrowException:
					try {
						throw propertyMapping.getMissingException().newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			final Property property = node.getProperty(propertyName);
			if (property.isMultiple()) {
				LOGGER.warn("Cannot handle multi-value properties yet.");
				return null;
			}

			return property.getValue().getString();

		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve property " + propertyName, e);
		}

	}

	public boolean hasProperty(PropertyMapping mapping) {
		try {
			return node.hasProperty(mapping.getPropertyName());
		} catch (RepositoryException e) {
			throw new ObjectMapperException("Could not retrieve property.", e);
		}
	}
}
