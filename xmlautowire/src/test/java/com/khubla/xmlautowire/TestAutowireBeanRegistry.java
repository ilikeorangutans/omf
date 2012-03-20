package com.khubla.xmlautowire;

import java.io.File;
import java.io.InputStream;

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
			final AutowireBeanRegistry dynabeanRegistry = new DefaultAutowireBeanRegistry();
			final InputStream is = TestAutowireBeanRegistry.class.getResourceAsStream("/dynabeans.xml");
			dynabeanRegistry.load(is);
			Assert.assertNotNull(dynabeanRegistry);
			Assert.assertNotNull(dynabeanRegistry.getBean("jcrsessionfactory"));
			File f = (File) dynabeanRegistry.getBean("omfactory");
			Assert.assertNotNull(f);
			Assert.assertTrue(f.getPath().compareTo("target/repository") == 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
