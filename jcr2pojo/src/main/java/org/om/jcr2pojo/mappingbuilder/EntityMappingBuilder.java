package org.om.jcr2pojo.mappingbuilder;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.jcr2pojo.exception.JCR2POJOException;
import org.om.jcr2pojo.mapping.BasicPropertyMap;
import org.om.jcr2pojo.mapping.BasicPropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class EntityMappingBuilder {

	/**
	 * build entity mapping
	 */
	public EntityMapping build(String jcrPath, Session session) throws JCR2POJOException {
		try {
			/*
			 * get the node
			 */
			final Node node = session.getRootNode().getNode(jcrPath);
			if (null != node) {
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
					 * field name
					 */
					final String fieldName = fixName(property.getName());
					/*
					 * mapping
					 */
					final BasicPropertyMapping propertyMapping = new BasicPropertyMapping(fieldName, property.getName());
					propertyMap.addField(fieldName, propertyMapping);
					propertyMap.addProperty(property.getName(), propertyMapping);
				}
				/*
				 * done
				 */
				return entityMappingImpl;
			} else {
				throw new Exception("Unable to find node '" + jcrPath + "'");
			}
		} catch (final Exception e) {
			throw new JCR2POJOException("Exception in build for path '" + jcrPath + "'", e);
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
}
