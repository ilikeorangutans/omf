package org.om.jcr2pojo.reveng;

import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Before;
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

	@Before
	public void setUp() {
		try {
			final InputStream is = TestJCRJavaPersistenceGenerator.class.getResourceAsStream("/test.xml");
			ImportUtil.importXML("test", is);
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
			/*
			 * get the root node
			 */
			final Node rootNode = session.getRootNode();
			Assert.assertNotNull(rootNode);
			/*
			 * go for it
			 */
			final JCRJavaPersistenceGenerator engine = new JCRJavaPersistenceGenerator(rootNode, "com.khubla.revenge.test", "target/reveng",
					new NodeNameClassNamingStrategy(), new DefaultPropertyNamingStrategy());
			engine.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}
