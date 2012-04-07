package org.om.examples.example4;

import junit.framework.Assert;

import org.junit.Test;
import org.om.dao.genericdao.GenericDAO;

import com.khubla.simpleioc.IOCBeanRegistry;
import com.khubla.simpleioc.impl.DefaultIOCBeanRegistry;
import com.om.examples.example4.Example4;
import com.om.examples.example4.pojo.MyPojo;
import com.om.examples.example4.service.MyService;

/**
 * 
 * @author tome
 * 
 */
public class TestExample {
	@Test
	public void test() {
		try {
			Example4.main(null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * test that we can get the service and that it has the DAO
	 */
	@Test
	public void test2() {
		try {
			IOCBeanRegistry ioc = new DefaultIOCBeanRegistry();
			ioc.load("/objectmanager.xml");
			MyService svc = (MyService) ioc.getBean("myservice");
			Assert.assertNotNull(svc);
			GenericDAO<MyPojo> dao = svc.getMypojodao();
			Assert.assertNotNull(dao);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
