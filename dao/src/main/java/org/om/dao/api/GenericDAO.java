package org.om.dao.api;

/**
 * 
 * @author tome
 * 
 * @param <T>
 */
public interface GenericDAO<T> {

	/**
	 * delete
	 */
	void delete(T t) throws Exception;

	/**
	 * get
	 */
	T get(String jcrPath) throws Exception;

	/**
	 * save
	 */
	void save(T t) throws Exception;

}