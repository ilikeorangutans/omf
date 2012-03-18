package org.om.core.impl.persistence.jcr.impl.entitymappingbuilder;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.mapping.BasicPropertyMap;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.core.impl.mapping.ImmutablePropertyMapping;
import org.om.core.impl.persistence.jcr.api.entitymappingbuilder.EntityMappingBuilder;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.util.PropertyTypeToClass;

/**
 * 
 * @author tome
 * 
 */
public class EntityMappingBuilderImpl implements EntityMappingBuilder {

	/**
	 * namespaces we want to not map
	 */
	private static final String[] ignoreNamespaces = { "jcr" };

	public EntityMapping build(Node node) throws JCRException {
		try {

			/*
			 * node type
			 */
			// NodeType nodeType = node.getPrimaryNodeType();
			// System.out.println(nodeType.getName());
			/*
			 * mapping
			 */
			final EntityMappingImpl entityMappingImpl = new EntityMappingImpl();
			final BasicPropertyMap propertyMap = new BasicPropertyMap();
			entityMappingImpl.setPropertyMap(propertyMap);
			/*
			 * get properties
			 */
			final PropertyIterator iter = node.getProperties();
			while (iter.hasNext()) {
				/*
				 * property
				 */
				final Property property = iter.nextProperty();
				/*
				 * map it?
				 */
				if (isMappableProperty(property.getName())) {
					/*
					 * field name
					 */
					final String fieldName = fixName(property.getName());
					/*
					 * jcr type
					 */
					final int jcrType = property.getType();
					/*
					 * java type
					 */
					final Class<?> type = PropertyTypeToClass.getClassForType(jcrType);
					/*
					 * mapping
					 */
					final ImmutablePropertyMapping propertyMapping = new ImmutablePropertyMapping(fieldName, false, null, property.getName(), type, null, null,
							null, jcrType);
					propertyMap.add(propertyMapping);
				}
			}
			/*
			 * done
			 */
			return entityMappingImpl;
		} catch (final Exception e) {
			throw new JCRException("Exception in build", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.om.core.impl.persistence.jcr.impl.mappingbuilder.EntityMappingBuilder
	 * #build(java.lang.String, javax.jcr.Session)
	 */
	public EntityMapping build(String jcrPath, Session session) throws JCRException {
		try {
			/*
			 * get the node
			 */
			final Node node = session.getRootNode().getNode(jcrPath);
			if (null != node) {
				return build(node);
			} else {
				throw new Exception("Unable to find node '" + jcrPath + "'");
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in build for path '" + jcrPath + "'", e);
		}
	}

	/**
	 * make a valid java field name from a jcr property name
	 */
	private String fixName(String name) {
		final int i = name.indexOf(":");
		if (-1 != i) {
			final char[] b = name.toCharArray();
			b[i + 1] = Character.toUpperCase(b[i + 1]);
			String ret = new String(b);
			ret = ret.replaceAll(":", "");
			return ret;
		} else {
			return name;
		}
	}

	/**
	 * check if we want to map this, or ignore it
	 */
	private boolean isMappableProperty(String propertyName) {
		final int i = propertyName.indexOf(":");
		if (-1 != i) {
			final String namespace = propertyName.substring(0, i);
			for (int j = 0; j < ignoreNamespaces.length; j++) {
				if (ignoreNamespaces[j].compareTo(namespace) == 0) {
					return false;
				}
			}
			return true;
		} else {
			/*
			 * if there is no namespace, map it
			 */
			return true;
		}
	}
}
