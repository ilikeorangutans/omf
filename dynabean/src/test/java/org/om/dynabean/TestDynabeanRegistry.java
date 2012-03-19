package org.om.dynabean;

import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.om.dynabean.impl.DefaultDynabeanRegistry;

/**
 * 
 * @author tome
 * 
 */
public class TestDynabeanRegistry {

	@Test
	public void test1() {
		try {
			final DynabeanRegistry dynabeanRegistry = new DefaultDynabeanRegistry();
			final InputStream is = TestDynabeanRegistry.class.getResourceAsStream("/dynabeans.xml");
			dynabeanRegistry.load(is);
			Assert.assertNotNull(dynabeanRegistry);
			Assert.assertNotNull(dynabeanRegistry.find("jcrsessionfactory"));
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
