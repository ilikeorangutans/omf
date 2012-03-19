package org.om.core.impl.persistence.jcr;

import javax.jcr.Session;

import org.om.core.impl.persistence.jcr.sessionfactory.impl.TransientRepositoryJCRSessionFactory;

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
