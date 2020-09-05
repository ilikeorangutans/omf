package org.om.core.impl.util;

import org.junit.*;
import org.junit.Test;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.field.*;
import org.om.core.impl.mapping.extractor.*;
import org.om.core.impl.test.*;

import junit.framework.Assert;

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
			final EntityWithPrimitiveProperties ewpp = new EntityWithPrimitiveProperties();
			ewpp.setId("tge");
			/*
			 * get the Id
			 */
			final String Id = (String) EntityUtils.getEntityId(mapping, ewpp);
			Assert.assertNotNull(Id);
			Assert.assertTrue(Id.compareTo("tge") == 0);
			/*
			 * set field
			 */
			final PropertyMapping propertyMapping = (PropertyMapping) mapping.getMappingByField("primitiveInt");
			EntityUtils.setEntityPropertyValue(propertyMapping, ewpp, new Integer(1414141));
			/*
			 * get it back
			 */
			final Integer ii = (Integer) EntityUtils.getEntityPropertyValue(propertyMapping, ewpp);
			Assert.assertTrue(ii.intValue() == 1414141);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
