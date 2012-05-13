package org.om.core.impl.persistence.jcr;

import java.io.File;

import javax.inject.Singleton;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;
import org.om.core.impl.ObjectMapperDefaultBindings;
import org.om.core.impl.persistence.jcr.sessionfactory.JCRSessionFactory;
import org.om.core.impl.persistence.jcr.sessionfactory.TestJcrFactory;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.Provides;

/**
 * {@link GuiceBerryModule} that sets up a {@link TransientRepository}. Will
 * inject the {@link Repository} instance and the {@link Session}.
 * 
 * @author Jakob Külzer
 */
public class TransientRepoTestEnv extends GuiceBerryModule {

	@Override
	protected void configure() {
		super.configure();
		bind(JCRSessionFactory.class).to(TestJcrFactory.class);
		bind(Session.class).toProvider(TestJcrFactory.class);
		install(new ObjectMapperDefaultBindings());
	}

	@Provides
	@Singleton
	public Repository createRepository() {
		System.out.println("SampleJcrEnv.createRepository() building repo");
		File dir = new File("target/transientrepo");
		return new TransientRepository(dir);
	}

}
