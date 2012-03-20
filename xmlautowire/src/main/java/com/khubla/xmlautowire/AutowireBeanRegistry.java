package com.khubla.xmlautowire;

import java.io.InputStream;

import com.khubla.xmlautowire.exception.AutowireBeanRegistryException;

/**
 * 
 * @author tome
 * 
 */
public interface AutowireBeanRegistry {

	/**
	 * get
	 */
	Object find(String name) throws AutowireBeanRegistryException;

	/**
	 * load
	 */
	void load(InputStream inputStream) throws AutowireBeanRegistryException;
}
