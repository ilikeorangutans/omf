package org.om.core.impl.persistence.jcr.impl;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.PropertiesConfiguredSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestPropertiesConfiguredSessionFactory extends BaseTestSessionFactory {

	@Override
	protected SessionFactory getSessionFactory() {
		return new PropertiesConfiguredSessionFactory("/configuredsessionfactory.properties");
	}
}
