package org.om.core.impl.util;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.field.PropertyMapping;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;
import org.om.core.impl.test.EntityWithPrimitiveProperties;

/**
 * @author tome
 */
@Ignore
public class TestEntityUtils {
	@Test
	public void test1() {
		try {
			/*
			 * get the mappings
			 */
			final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
			/*
			 * an entity with an Id
			 */
			EntityWithPrimitiveProperties ewpp = new EntityWithPrimitiveProperties();
			ewpp.setId("tge");
			/*
			 * get the Id
			 */
			String Id = (String) EntityUtils.getEntityId(mapping, ewpp);
			Assert.assertNotNull(Id);
			Assert.assertTrue(Id.compareTo("tge") == 0);
			/*
			 * set field
			 */
			PropertyMapping propertyMapping = (PropertyMapping) mapping.getMappingByField("primitiveInt");
			EntityUtils.setEntityPropertyValue(propertyMapping, ewpp, new Integer(1414141));
			/*
			 * get it back
			 */
			Integer ii = (Integer) EntityUtils.getEntityPropertyValue(propertyMapping, ewpp);
			Assert.assertTrue(ii.intValue() == 1414141);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
