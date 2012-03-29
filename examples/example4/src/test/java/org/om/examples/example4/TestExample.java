package org.om.examples.example4;

import junit.framework.Assert;

import org.junit.Test;

import com.om.examples.example4.Example4;

/**
 * 
 * @author tome
 * 
 */
public class TestExample {
	@Test
	public void test() {
		try {
			Example4.main(null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
