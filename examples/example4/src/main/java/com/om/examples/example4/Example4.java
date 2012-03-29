package com.om.examples.example4;

import com.om.examples.example4.service.MyService;
import com.om.examples.example4.service.MyServiceFactory;

/**
 * 
 * @author tome
 * 
 */
public class Example4 {
	/**
	 * void main
	 */
	public static void main(String args[]) throws java.io.IOException, java.io.FileNotFoundException {
		final MyService myService = MyServiceFactory.getInstance().getMyService();
		myService.doStuff();
	}
}
