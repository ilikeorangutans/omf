package org.om.dao.genericdao;

import org.om.dao.genericdao.impl.GenericDAOImpl;

/**
 * @author tome
 */
public class TestEntityDAO extends GenericDAOImpl<TestEntity> implements GenericDAO<TestEntity> {
   /**
    * ctor
    */
   public TestEntityDAO() {
      super(TestEntity.class);
   }
}
