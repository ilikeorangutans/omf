package org.om.core.impl.util;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.extractor.EntityMappingExtractorImpl;

/**
 * 
 * @author tome
 * 
 */
public class TestEntityUtils {

	@Test
	public void test1() {
		try {
			/*
			 * an entity with an Id
			 */
			EntityWithPrimitiveProperties ewpp = new EntityWithPrimitiveProperties();
			ewpp.setId("tge");
			/*
			 * get the mappings
			 */
			final EntityMapping mapping = new EntityMappingExtractorImpl().extract(EntityWithPrimitiveProperties.class);
			/*
			 * get the Id
			 */
			String Id = (String) EntityUtils.getEntityId(mapping, ewpp);
			Assert.assertNotNull(Id);
			Assert.assertTrue(Id.compareTo("tge") == 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
