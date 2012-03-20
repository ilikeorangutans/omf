package org.om.jcr2pojo.reveng;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;
import org.om.core.impl.persistence.jcr.util.ImportUtil;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.DefaultPropertyNamingStrategy;
import org.om.jcr2pojo.entitymappingbuilder.namingstrategy.impl.NodeNameClassNamingStrategy;

/**
 * 
 * @author tome
 * 
 */
public class TestJCRJavaPersistenceGenerator {

	@Test
	public void testAllFiles() {
		try {
			final File[] files = new File("src/test/resources/examples").listFiles();
			for (int i = 0; i < files.length; i++) {
				/*
				 * drop a message
				 */
				System.out.println("Generating java persistence for JCR XML:" + files[i].getName());
				/*
				 * test name
				 */
				final String testname = files[i].getName();
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
