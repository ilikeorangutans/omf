package org.om.dao.genericdao;

import java.util.UUID;

import org.om.core.api.path.Path;
import org.om.dao.exception.DAOException;

/**
 * @author tome
 * @param <T>
 */
public interface GenericDAO<T> {
   /**
    * delete
    */
   void delete(T t) throws DAOException;

   /**
    * get
    */
   T get(Path path) throws DAOException;

   /**
    * get
    */
   T get(String path) throws DAOException;

   /**
    * get
    */
   T get(UUID uuid) throws DAOException;

   /**
    * save
    */
   void save(T t) throws DAOException;
}