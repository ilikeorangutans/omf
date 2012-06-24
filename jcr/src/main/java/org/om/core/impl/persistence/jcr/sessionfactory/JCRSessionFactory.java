package org.om.core.impl.persistence.jcr.sessionfactory;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.sessionfactory.impl.PropertiesConfiguredJCRSessionFactory;

import com.google.inject.ImplementedBy;

/**
 * A factory for JCR sessions.
 * 
 * @author Jakob Külzer
 * @author tome
 */
@ImplementedBy(PropertiesConfiguredJCRSessionFactory.class)
public interface JCRSessionFactory {

	/**
	 * Returns a JCR Session.
	 */
	Session getSession();
}
