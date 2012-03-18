package org.om.jcr2pojo.reveng;

import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.namingstrategy.DefaultPropertyNamingStrategy;
import org.om.core.impl.persistence.jcr.impl.entitymappingbuilder.namingstrategy.NodeNameClassNamingStrategy;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredJCRSessionFactory;
import org.om.core.impl.persistence.jcr.util.ImportUtil;

/**
 * 
 * @author tome
 * 
 */
public class TestJCRJavaPersistenceGenerator {

	@Test
	public void test1() {
		try {
			/*
			 * import some data
			 */
			final InputStream is = TestJCRJavaPersistenceGenerator.class.getResourceAsStream("/test.xml");
			ImportUtil.importXML("test", is);
			/*
			 * get a session
			 */
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			/*
			 * get the node
			 */
			final Node rootNode = session.getRootNode().getNode("test");
			Assert.assertNotNull(rootNode);
			/*
			 * go for it
			 */
			final JCRJavaPersistenceGenerator engine = new JCRJavaPersistenceGenerator(rootNode, "com.khubla.revenge.test", "target/reveng/test1",
					new NodeNameClassNamingStrategy(), new DefaultPropertyNamingStrategy());
			engine.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test
	public void test2() {
		try {
			/*
			 * import some data
			 */
			final InputStream is = TestJCRJavaPersistenceGenerator.class.getResourceAsStream("/alfresco.xml");
			ImportUtil.importXML("alfresco", is);
			/*
			 * get a session
			 */
			final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
			Assert.assertNotNull(session);
			/*
			 * get the node
			 */
			final Node rootNode = session.getRootNode().getNode("alfresco");
			Assert.assertNotNull(rootNode);
			/*
			 * go for it
			 */
			final JCRJavaPersistenceGenerator engine = new JCRJavaPersistenceGenerator(rootNode, "com.khubla.revenge.test", "target/reveng/test2",
					new NodeNameClassNamingStrategy(), new DefaultPropertyNamingStrategy());
			engine.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

}
