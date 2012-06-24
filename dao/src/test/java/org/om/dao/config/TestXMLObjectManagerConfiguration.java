package org.om.dao.config;

import junit.framework.Assert;

import org.junit.Test;
import org.om.dao.config.impl.XMLObjectManagerConfiguration;

/**
 * @author tome
 */
public class TestXMLObjectManagerConfiguration {
   @Test
   public void testInstantiation() {
      try {
         final ObjectManagerConfiguration objectManagerConfiguration = XMLObjectManagerConfiguration.getObjectManagerConfiguration();
         Assert.assertNotNull(objectManagerConfiguration);
         Assert.assertNotNull(objectManagerConfiguration.getPersistenceContext());
         Assert.assertNotNull(objectManagerConfiguration.getSessionFactory());
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
