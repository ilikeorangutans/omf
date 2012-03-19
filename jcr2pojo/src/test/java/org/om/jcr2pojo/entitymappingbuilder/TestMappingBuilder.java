package org.om.jcr2pojo.entitymappingbuilder;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;
import org.om.jcr2pojo.entitymappingbuilder.impl.EntityMappingBuilderImpl;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.DefaultPropertyNamingStrategy;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.NodeIdentifierClassNamingStrategy;

/**
 * 
 * @author tome
 * 
 */
public class TestMappingBuilder {
	@Before
	public void setUp() {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			/*
			 * get the root node
			 */
			final Node rootNode = session.getRootNode();
			Assert.assertNotNull(rootNode);
			/*
			 * add two nodes, one of which has properties
			 */
			final Node foo = rootNode.addNode("foo");
			final Node bar = foo.addNode("bar");
			bar.setProperty("foobar", "Horray!!");
			bar.setProperty("mycoolfield", "1000000");
			/*
			 * save to the jcr
			 */
			session.save();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@After
	public void tearDown() {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			/*
			 * get the root node
			 */
			final Node rootNode = session.getRootNode();
			Assert.assertNotNull(rootNode);
			/*
			 * remove foo
			 */
			rootNode.getNode("foo/bar").remove();
			rootNode.getNode("foo").remove();
			/*
			 * save to the jcr
			 */
			session.save();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test1() {
		try {
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			final EntityMappingBuilder entityMappingBuilder = new EntityMappingBuilderImpl(new NodeIdentifierClassNamingStrategy(),
					new DefaultPropertyNamingStrategy());
			final EntityMapping entityMapping = entityMappingBuilder.build("foo/bar", session);
			Assert.assertNotNull(entityMapping);
			Assert.assertNotNull(entityMapping.getPropertyMappings());
			Assert.assertTrue(entityMapping.getPropertyMappings().getSize() == 2);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
