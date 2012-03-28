package org.om.examples.example2;

import junit.framework.Assert;

import org.junit.Test;

import com.om.examples.example2.Example2;

/**
 * 
 * @author tome
 * 
 */
public class TestExample {
	@Test
	public void test() {
		try {
			Example2.main(null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
