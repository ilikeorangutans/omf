package org.om.jcr2pojo.reveng;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.api.entitymappingbuilder.EntityMappingBuilder;
import org.om.core.impl.persistence.jcr.exception.JCRException;
import org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.EntityMappingBuilderImpl;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredJCRSessionFactory;
import org.om.jcr2pojo.classgenerator.POJOGenerator;

/**
 * 
 * @author tome
 * 
 */
public class Engine {
	private void generateJava(EntityMapping entityMapping) throws JCRException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final POJOGenerator pojoGenerator = new POJOGenerator();
			pojoGenerator.generatePOJO("TestClass", "com.khubla", entityMapping, baos);
			System.out.println(baos.toString());
		} catch (final Exception e) {
			throw new JCRException("Exception in generateJava", e);
		}
	}

	private Collection<EntityMapping> generateMappings() throws JCRException {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			if (null != session) {

				final Node rootNode = session.getRootNode();
				if (null != rootNode) {
					return mapNode(rootNode);
				} else {
					return null;
				}
			} else {
				throw new JCRException("Unable to get session");
			}

		} catch (final Exception e) {
			throw new JCRException("Exception in generateMappings", e);
		}
	}

	private Collection<EntityMapping> mapNode(Node node) throws JCRException {
		try {
			final Collection<EntityMapping> ret = new ArrayList<EntityMapping>();
			final NodeIterator iter = node.getNodes();
			while (iter.hasNext()) {
				final Node n = iter.nextNode();
				/*
				 * map
				 */
				final EntityMappingBuilder entityMapper = new EntityMappingBuilderImpl();
				final EntityMapping mapping = entityMapper.build(n);
				ret.add(mapping);
				/*
				 * node has nodes?
				 */
				if (node.hasNodes()) {
					final Collection<EntityMapping> subCollection = mapNode(n);
					ret.addAll(subCollection);
				}
			}
			return ret;
		} catch (final Exception e) {
			throw new JCRException("Exception in mapNode", e);
		}
	}

	public void reverseEngineer() throws JCRException {
		try {
			/*
			 * get the mappings
			 */
			final Collection<EntityMapping> mappings = generateMappings();
			if ((null != mappings) && (mappings.size() > 0)) {
				/*
				 * walk
				 */
				final Iterator<EntityMapping> iter = mappings.iterator();
				while (iter.hasNext()) {
					final EntityMapping entityMapping = iter.next();
					if (entityMapping.getPropertyMappings().getSize() > 0) {
						generateJava(entityMapping);
					}
				}
			}
		} catch (final Exception e) {
			throw new JCRException("Exception in reverseEngineer", e);
		}
	}
}
