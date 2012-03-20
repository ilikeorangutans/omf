package org.om.dynabean;

import java.io.InputStream;

import org.om.dynabean.exception.DynabeanException;

/**
 * 
 * @author tome
 * 
 */
public interface DynabeanRegistry {

	/**
	 * get
	 */
	Object find(String name) throws DynabeanException;

	/**
	 * load
	 */
	void load(InputStream inputStream) throws DynabeanException;
}
