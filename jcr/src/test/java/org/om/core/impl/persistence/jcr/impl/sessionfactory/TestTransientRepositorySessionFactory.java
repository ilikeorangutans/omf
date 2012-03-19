package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.TransientRepositoryJCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestTransientRepositorySessionFactory extends BaseTestSessionFactory {

	@Override
	protected JCRSessionFactory getSessionFactory() {
		return new TransientRepositoryJCRSessionFactory("target/testtransientrepo");
	}

}