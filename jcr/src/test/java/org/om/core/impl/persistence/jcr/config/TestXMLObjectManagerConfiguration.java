package org.om.core.impl.persistence.jcr.config;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.impl.persistence.jcr.api.config.ObjectManagerConfiguration;
import org.om.core.impl.persistence.jcr.impl.config.XMLObjectManagerConfiguration;

/**
 * 
 * @author tome
 * 
 */
public class TestXMLObjectManagerConfiguration {

	@Test
	public void testInstantiation() {
		try {
			ObjectManagerConfiguration objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
			Assert.assertNotNull(objectManagerConfiguration);
			Assert.assertNotNull(objectManagerConfiguration.getJCRSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
