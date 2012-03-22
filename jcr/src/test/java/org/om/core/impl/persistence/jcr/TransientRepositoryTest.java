package org.om.core.impl.persistence.jcr;

import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.SingletonJCRSessionFactoryDecorator;
import org.om.core.impl.persistence.jcr.sessionfactory.impl.TransientRepositoryJCRSessionFactory;

/**
 * 
 * @author tome
 * 
 */
public class TransientRepositoryTest extends BaseJCRSessionTest {

	@Override
	protected JCRSessionFactory getSessionFactory() {
		/*
		 * here we need to ensure that we always get the same session per
		 * thread.
		 */
		return new SingletonJCRSessionFactoryDecorator(new TransientRepositoryJCRSessionFactory("target/repository"));
	}
}
