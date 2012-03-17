package org.om.core.impl.persistence.jcr.impl;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.TransientRepositorySessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestTransientRepositorySessionFactory extends BaseTestSessionFactory {

	@Override
	protected SessionFactory getSessionFactory() {
		return new TransientRepositorySessionFactory("target/testtransientrepo");
	}

}