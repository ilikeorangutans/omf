package org.om.jcr2pojo.classgenerator;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author tome
 * 
 */
public class TestDAOGenerator {
	@Test
	public void test1() {
		try {

			/*
			 * generate some java
			 */
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final DAOGenerator daoGenerator = new DAOGenerator();
			daoGenerator.generateDAO("TestClass", "com.khubla", baos);
			System.out.println(baos.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
