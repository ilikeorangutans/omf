package com.om.examples.example4.service;

import org.om.dao.annotation.transactional.filter.TransactionalIOCInstantiationFilter;

import com.khubla.simpleioc.IOCBeanRegistry;
import com.khubla.simpleioc.impl.DefaultIOCBeanRegistry;

/**
 * 
 * @author tome
 * 
 */
public class MyServiceFactory {

	/**
	 * singleton
	 */
	private static MyServiceFactory instance = null;

	public static MyServiceFactory getInstance() {
		if (null == instance) {
			instance = new MyServiceFactory();
		}
		return instance;
	}

	/**
	 * IOC container
	 */
	private final IOCBeanRegistry ioc;

	/**
	 * load the beans and pass a TransactionalIOCInstantiationFilter
	 */
	private MyServiceFactory() {
		ioc = new DefaultIOCBeanRegistry();
		ioc.load("/objectmanager.xml", new TransactionalIOCInstantiationFilter());
	}

	/**
	 * this returns a proxy, which wraps the actual MyService, in a proxy which
	 * handles JCR transactions
	 */
	public MyService getMyService() {
		return (MyService) ioc.getBean("myservice");
	}
}
