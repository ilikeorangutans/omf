package com.khubla.xmlautowire;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import com.khubla.xmlautowire.impl.DefaultAutowireBeanRegistry;

/**
 * 
 * @author tome
 * 
 */
public class TestAutowireBeanRegistry {

	@Test
	public void test1() {
		try {
			final AutowireBeanRegistry autobeanRegistry = new DefaultAutowireBeanRegistry();
			autobeanRegistry.load();
			Assert.assertNotNull(autobeanRegistry);
			Assert.assertNotNull(autobeanRegistry.getBean("jcrsessionfactory"));
			File f = (File) autobeanRegistry.getBean("omfactory");
			Assert.assertNotNull(f);
			Assert.assertTrue(f.getPath().compareTo("target/repository") == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
