package org.om.dao.config;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.om.dao.config.impl.XMLObjectManagerConfiguration;

/**
 * 
 * @author tome
 * 
 */
public class TestXMLObjectManagerConfiguration {

	@Test
	@Ignore
	public void testInstantiation() {
		try {
			final ObjectManagerConfiguration objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
			Assert.assertNotNull(objectManagerConfiguration);
			Assert.assertNotNull(objectManagerConfiguration.getJCRSessionFactory());
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
