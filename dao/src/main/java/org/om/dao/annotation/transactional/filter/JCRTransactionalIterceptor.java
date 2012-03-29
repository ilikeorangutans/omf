package org.om.dao.annotation.transactional.filter;

import java.lang.reflect.Method;

import javax.jcr.Session;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.jackrabbit.rmi.client.ClientXASession;
import org.om.core.impl.persistence.jcr.JcrPersistenceContext;
import org.om.dao.annotation.transactional.Transactional;
import org.om.dao.util.SessionUtil;

/**
 * 
 * @author tome
 * 
 */
public class JCRTransactionalIterceptor implements MethodInterceptor {

	private final Object realObj;

	public JCRTransactionalIterceptor(Object realObj) {
		this.realObj = realObj;
	}

	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object result;
		/*
		 * get @Transactional annotation on field
		 */
		final Transactional transactional = method.getAnnotation(Transactional.class);
		if (null != transactional) {
			/*
			 * get the JCR context
			 */
			JcrPersistenceContext jcrPersistenceContext = SessionUtil.getJcrPersistenceContext();
			if (null != jcrPersistenceContext) {
				/*
				 * get JCR session
				 */
				Session session = jcrPersistenceContext.getSession();
				if (null != session) {
					/*
					 * upcast
					 */
					ClientXASession xaSession = (ClientXASession) session;
					/*
					 * transaction id
					 */
					Xid xid = new OMXid();
					try {
						/*
						 * start tx
						 */
						xaSession.start(xid, XAResource.TMNOFLAGS);
						/*
						 * invoke
						 */
						result = method.invoke(realObj, objects);
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
		} else {
			result = method.invoke(realObj, objects);
		}
		return result;
	}
}
