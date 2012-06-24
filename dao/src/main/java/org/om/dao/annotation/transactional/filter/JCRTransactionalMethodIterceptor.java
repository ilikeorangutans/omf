package org.om.dao.annotation.transactional.filter;

import java.lang.reflect.Method;

import javax.jcr.Session;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.jackrabbit.rmi.client.ClientXASession;
import org.om.core.api.persistence.PersistenceContext;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.dao.annotation.transactional.Transactional;
import org.om.dao.exception.DAOException;
import org.om.dao.util.SessionUtil;

/**
 * @author tome
 */
public class JCRTransactionalMethodIterceptor implements MethodInterceptor {
   public Object invoke(MethodInvocation methodInvocation) throws Throwable {
      try {
         Object result;
         final Method method = methodInvocation.getMethod();
         /*
          * get @Transactional annotation on field
          */
         final Transactional transactional = method.getAnnotation(Transactional.class);
         if (null != transactional) {
            /*
             * get the JCR context
             */
            final PersistenceContext persistenceContext = SessionUtil.getPersistenceContext();
            if (null != persistenceContext) {
               /*
                * get JCR session. ugh. Sorry.
                */
               final Session session = ((JcrPersistenceContext) persistenceContext).getSession();
               if (null != session) {
                  /*
                   * upcast
                   */
                  final ClientXASession xaSession = (ClientXASession) session;
                  /*
                   * transaction id
                   */
                  final Xid xid = new OMXid();
                  try {
                     /*
                      * start tx
                      */
                     xaSession.start(xid, XAResource.TMNOFLAGS);
                     /*
                      * invoke
                      */
                     result = methodInvocation.proceed();
                     /*
                      * commit!
                      */
                     xaSession.commit(xid, true);
                  } catch (final Exception e) {
                     /*
                      * uh-oh, rollback
                      */
                     xaSession.rollback(xid);
                     throw new RuntimeException("Exception in invoke ", e);
                  }
               } else {
                  throw new Exception("Unable to get JCR Session");
               }
            } else {
               throw new Exception("Unable to get JCR Persistence Context");
            }
         }
         else {
            result = methodInvocation.proceed();
         }
         return result;
      } catch (final Exception e) {
         throw new DAOException("Exception in invoke", e);
      }
   }
}
