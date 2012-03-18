package org.om.jcr2pojo.reveng;

import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.om.core.impl.persistence.jcr.util.ImportUtil;

/**
 * 
 * @author tome
 * 
 */
public class TestEngine {

	@Before
	public void setUp() {
		try {
			final InputStream is = TestEngine.class.getResourceAsStream("/test.xml");
			ImportUtil.importXML("test", is);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test1() {
		try {
			final Engine engine = new Engine();
			engine.reverseEngineer();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}
