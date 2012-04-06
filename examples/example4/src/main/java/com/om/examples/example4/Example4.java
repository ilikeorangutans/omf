package com.om.examples.example4;

import com.khubla.simpleioc.IOCBeanRegistry;
import com.khubla.simpleioc.impl.DefaultIOCBeanRegistry;
import com.om.examples.example4.service.MyService;

/**
 * 
 * @author tome
 * 
 */
public class Example4 {
	/**
	 * service
	 */
	private static IOCBeanRegistry ioc = new DefaultIOCBeanRegistry();

	/**
	 * void main
	 */
	public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
		ioc.load("/objectmanager.xml");
		MyService svc = (MyService) ioc.getBean("myservice");
		svc.doStuff();
	}
}
