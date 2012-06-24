package org.om.dao.exception;

/**
 * @author tome
 */
public class DAOException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public DAOException(Exception e) {
      super(e);
   }

   public DAOException(String e) {
      super(e);
   }

   public DAOException(String s, Exception e) {
      super(s, e);
   }
}