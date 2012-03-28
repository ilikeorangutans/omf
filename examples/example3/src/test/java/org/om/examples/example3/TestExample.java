package org.om.examples.example3;

import junit.framework.Assert;

import org.junit.Test;

import com.om.examples.example3.Example3;

/**
 * 
 * @author tome
 * 
 */
public class TestExample {
	@Test
	public void test() {
		try {
			Example3.main(null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
