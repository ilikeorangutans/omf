package org.om.dao.impl;

import junit.framework.Assert;

import org.junit.Test;
import org.om.core.api.session.Session;

/**
 * 
 * @author tome
 * 
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
