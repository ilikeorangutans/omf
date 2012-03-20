package org.om.jcr2pojo.classgenerator;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.mapping.BasicPropertyMap;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.core.impl.mapping.ImmutablePropertyMapping;

/**
 * 
 * @author tome
 * 
 */
public class TestPOJOGenerator {

	@Test
	public void test1() {
		try {
			/*
			 * build some mappings
			 */
			final EntityMappingImpl entityMappingImpl = new EntityMappingImpl("TestClass");
			final BasicPropertyMap propertyMap = new BasicPropertyMap();
			entityMappingImpl.setPropertyMap(propertyMap);
			/*
			 * add some fields
			 */
			propertyMap.add(new ImmutablePropertyMapping("a", false, null, "om:a", String.class, null, null, null));
			propertyMap.add(new ImmutablePropertyMapping("b", false, null, "om:b", Integer.class, null, null, null));
			propertyMap.add(new ImmutablePropertyMapping("c", false, null, "om:c", Float.class, null, null, null));
			propertyMap.add(new ImmutablePropertyMapping("d", false, null, "om:d", Boolean.class, null, null, null));
			/*
			 * generate some java
			 */
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final POJOGenerator pojoGenerator = new POJOGenerator();
			pojoGenerator.generatePOJO("com.khubla", entityMappingImpl, baos);
			System.out.println(baos.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
