package org.om.jcr2pojo.mappingbuilder;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.mapping.BasicPropertyMap;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.core.impl.mapping.ImmutablePropertyMapping;
import org.om.jcr2pojo.classexporter.ClassExporter;

/**
 * 
 * @author tome
 * 
 */
public class TestClassExporter {

	@Test
	public void test1() {
		try {
			/*
			 * build some mappings
			 */
			final EntityMappingImpl entityMappingImpl = new EntityMappingImpl();
			final BasicPropertyMap propertyMap = new BasicPropertyMap();
			entityMappingImpl.setPropertyMap(propertyMap);
			/*
			 * add some fields
			 */
			propertyMap.addField("a", new ImmutablePropertyMapping("a", false, null, "om:a", String.class, null, null, null));
			propertyMap.addField("b", new ImmutablePropertyMapping("a", false, null, "om:b", String.class, null, null, null));
			propertyMap.addField("c", new ImmutablePropertyMapping("a", false, null, "om:c", String.class, null, null, null));
			propertyMap.addField("d", new ImmutablePropertyMapping("a", false, null, "om:d", String.class, null, null, null));
			/*
			 * generate some java
			 */
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final ClassExporter classExporter = new ClassExporter();
			classExporter.exportClass("TestClass", "com.khubla", entityMappingImpl, baos);
			System.out.println(baos.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
