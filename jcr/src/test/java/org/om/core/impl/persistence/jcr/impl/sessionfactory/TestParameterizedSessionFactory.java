package org.om.core.impl.persistence.jcr.impl.sessionfactory;

import org.om.core.impl.persistence.jcr.api.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.impl.sessionfactory.ParameterizedJCRSessionFactory;

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
	protected JCRSessionFactory getSessionFactory() {
		return new ParameterizedJCRSessionFactory(JACKRABBIT_2_4_0_RMI, null, "admin", "admin");
	}
}
