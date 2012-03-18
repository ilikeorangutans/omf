package org.om.jcr2pojo.mappingbuilder;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.mapping.EntityMappingImpl;
import org.om.jcr2pojo.classexporter.ClassExporter;
import org.om.jcr2pojo.mapping.BasicPropertyMap;
import org.om.jcr2pojo.mapping.BasicPropertyMapping;

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
			propertyMap.addField("a", new BasicPropertyMapping("a", "om:a", String.class));
			propertyMap.addField("b", new BasicPropertyMapping("b", "om:a", String.class));
			propertyMap.addField("c", new BasicPropertyMapping("c", "om:a", String.class));
			propertyMap.addField("d", new BasicPropertyMapping("d", "om:a", String.class));
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
