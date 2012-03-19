package org.om.dao.genericdao;

import java.util.UUID;

import org.om.core.api.path.Path;

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
	T get(Path path) throws Exception;

	/**
	 * get
	 */
	T get(UUID uuid) throws Exception;

	/**
	 * save
	 */
	void save(T t) throws Exception;

}