package org.om.dao.annotation.transactional.filter;

import javax.transaction.xa.Xid;

/**
 * OM Xid
 * 
 * @author tome
 */
public class OMXid implements Xid {
   public byte[] getBranchQualifier() {
      return new byte[0];
   }

   public int getFormatId() {
      return 0;
   }

   public byte[] getGlobalTransactionId() {
      return new byte[0];
   }
}
