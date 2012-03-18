package org.om.dao.impl;

import org.om.dao.api.GenericDAO;

/**
 * 
 * @author tome
 * 
 */
public class TestEntityDAO extends GenericDAOImpl<TestEntity> implements GenericDAO<TestEntity> {
	/**
	 * ctor
	 */
	public TestEntityDAO() {
		super(TestEntity.class);
	}
}
