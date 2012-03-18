package org.om.jcr2pojo.reveng;

import java.io.File;
import java.io.FileInputStream;
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
	public void testAllFiles() {
		try {
			File[] files = new File("src/test/resources/examples").listFiles();
			for (int i = 0; i < files.length; i++) {
				/*
				 * test name
				 */
				String testname = "test" + i;
				/*
				 * import some data
				 */
				final InputStream is = new FileInputStream(files[i]);
				ImportUtil.importXML(testname, is);
				/*
				 * get a session
				 */
				final Session session = new PropertiesConfiguredJCRSessionFactory().getSession();
				Assert.assertNotNull(session);
				/*
				 * get the node
				 */
				final Node rootNode = session.getRootNode().getNode(testname);
				Assert.assertNotNull(rootNode);
				/*
				 * go for it
				 */
				final JCRJavaPersistenceGenerator engine = new JCRJavaPersistenceGenerator(rootNode, "com.khubla.revenge.test", "target/reveng/" + testname,
						new NodeNameClassNamingStrategy(), new DefaultPropertyNamingStrategy());
				engine.execute();
			}

		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
