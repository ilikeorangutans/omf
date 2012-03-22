package org.om.examples.example1;

import junit.framework.Assert;

import org.junit.Test;

import com.om.examples.Example1;

/**
 * 
 * @author tome
 * 
 */
public class TestExample {
	@Test
	public void test() {
		try {
			Example1.main(null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
