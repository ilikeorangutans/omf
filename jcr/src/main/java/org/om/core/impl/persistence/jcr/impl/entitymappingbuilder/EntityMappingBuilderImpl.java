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

/**
 * 
 * @author tome
 * 
 */
public class EntityMappingBuilderImpl implements EntityMappingBuilder {

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
					final ImmutablePropertyMapping propertyMapping = new ImmutablePropertyMapping(fieldName, false, null, property.getName(), String.class,
							null, null, null);
					propertyMap.add(propertyMapping);
				}
				/*
				 * done
				 */
				return entityMappingImpl;
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
}
