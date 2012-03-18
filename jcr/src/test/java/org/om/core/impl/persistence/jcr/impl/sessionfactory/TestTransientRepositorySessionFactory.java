package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;

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