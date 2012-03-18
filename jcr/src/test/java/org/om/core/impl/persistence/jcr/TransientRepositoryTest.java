package org.om.core.impl.persistence.jcr;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.impl.sessionfactory.TransientRepositoryJCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TransientRepositoryTest extends BaseJCRSessionTest {

	@Override
	protected Session getSession() {
		return new TransientRepositoryJCRSessionFactory("target/repository").getSession();
	}

}
