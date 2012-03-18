package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TestPropertiesConfiguredSessionFactory extends BaseTestSessionFactory {

	@Override
	protected JCRSessionFactory getSessionFactory() {
		return new PropertiesConfiguredJCRSessionFactory("/configuredsessionfactory.properties");
	}
}
