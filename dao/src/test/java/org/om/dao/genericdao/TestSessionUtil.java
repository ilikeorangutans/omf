package org.om.dao.genericdao;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.api.session.Session;
import org.om.dao.util.SessionUtil;

/**
 * @author tome
 */
public class TestSessionUtil {
   @Test
   public void test1() {
      try {
         final Session session = SessionUtil.getSession();
         Assert.assertNotNull(session);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
