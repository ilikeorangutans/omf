package org.om.core.impl.persistence.jcr;

import org.junit.Ignore;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.TransientRepositoryJCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
@Ignore
public class TransientRepositoryTest extends BaseJCRSessionTest {

	@Override
	protected JCRSessionFactory getSessionFactory() {
		return new TransientRepositoryJCRSessionFactory("target/repository");

	}

}
