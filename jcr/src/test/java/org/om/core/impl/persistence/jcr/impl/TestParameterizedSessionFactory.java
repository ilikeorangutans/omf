package org.om.core.impl.persistence.jcr.impl;

import org.om.core.impl.persistence.jcr.api.sessionfactory.SessionFactory;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ParameterizedSessionFactory;

/**
 * 
 * @author tome
 *         <p>
 *         This test needs JackRabbit 2.4.0 running in standalone mode on
 *         localhost
 *         </p>
 * 
 */
public class TestParameterizedSessionFactory extends BaseTestSessionFactory {
	/**
	 * jackrabbit 2.4.0
	 */
	private static final String JACKRABBIT_2_4_0_RMI = "http://localhost:8080/rmi";

	@Override
	protected SessionFactory getSessionFactory() {
		return new ParameterizedSessionFactory(JACKRABBIT_2_4_0_RMI, null, "admin", "admin");
	}
}
