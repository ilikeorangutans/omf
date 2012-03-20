package org.om.maven;

import java.io.File;

import junit.framework.Assert;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 * @author tome
 */
public class TestRevengMojo extends AbstractMojoTestCase {
	/**
	 * file
	 */
	private static final String POMFILE = "src/test/resources/TestRevengMojo-pom.xml";
	/**
	 * goal
	 */
	private static final String GOAL = "reveng";

	@Override
	protected void setUp() throws Exception {
		// required
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// required
		super.tearDown();
	}

	/**
	 * Basic test of execution
	 */
	public void testExecution() throws Exception {
		try {
			final File pom = getTestFile(POMFILE);
			assertNotNull(pom);
			assertTrue(pom.exists());
			final RevengMojo wasMojo = (RevengMojo) lookupMojo(GOAL, pom);
			assertNotNull(wasMojo);
			wasMojo.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Basic test of instatiation
	 */
	public void testInstatiation() throws Exception {
		try {
			final File pom = getTestFile(POMFILE);
			assertNotNull(pom);
			assertTrue(pom.exists());
			final RevengMojo wasMojo = (RevengMojo) lookupMojo(GOAL, pom);
			assertNotNull(wasMojo);
		} catch (final Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Basic test of parameter setting
	 */
	public void testParameterSetting() throws Exception {
		try {
			final File pom = getTestFile(POMFILE);
			assertNotNull(pom);
			assertTrue(pom.exists());
			final RevengMojo wasMojo = (RevengMojo) lookupMojo(GOAL, pom);
			assertNotNull(wasMojo);
			Assert.assertTrue(wasMojo.getUrl().compareTo("http://localhost:8080/rmi") == 0);
			Assert.assertTrue(wasMojo.getUsername().compareTo("admin") == 0);
			Assert.assertTrue(wasMojo.getPassword().compareTo("admin") == 0);
			Assert.assertTrue(wasMojo.getWorkspace() == null);
			Assert.assertTrue(wasMojo.getNamespace().compareTo("com.khubla") == 0);
			Assert.assertTrue(wasMojo.getJcrRoot() == null);
			Assert.assertTrue(wasMojo.getTargetDir().compareTo("target/om/") == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}