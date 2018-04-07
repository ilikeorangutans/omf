package org.om.core.impl.persistence.jcr.test;

import java.io.File;
import java.net.URISyntaxException;

import javax.inject.Singleton;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.jackrabbit.core.TransientRepository;

import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestId;
import com.google.guiceberry.TestScoped;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;

/**
 * {@link GuiceBerryModule} that sets up a {@link TransientRepository}. Will inject the {@link Repository} instance and the {@link Session}.
 *
 * @author Jakob Külzer
 */
public class TransientRepoTestEnv extends GuiceBerryModule {
   @Override
   protected void configure() {
      super.configure();
      bind(Session.class).toProvider(TestJcrFactory.class).in(TestScoped.class);
      install(new ObjectMapperDefaultBindings());
   }

   @Provides
   @Singleton
   public Repository createRepository() throws URISyntaxException {
      System.out.println("TransientRepoTestEnv.createRepository() Starting up repository...");
      final File dir = new File("target/transientrepo");
      final File configFile = new File(getClass().getClassLoader().getResource("repository.xml").toURI());
      System.out.println("TransientRepoTestEnv.createRepository() Loading repository configuration from " + configFile.getAbsolutePath());
      return new TransientRepository(configFile, dir);
   }

   @Provides
   public TestWrapper getTestWrapper(final TestId testId, final TearDownAccepter tearDownAccepter, final Session session) {
      final TestWrapper testWrapper = new TestWrapper() {
         @Override
         public void toRunBeforeTest() {
            tearDownAccepter.addTearDown(new TearDown() {
               @Override
               public void tearDown() throws Exception {
                  System.out.println("TearDown.tearDown() Closing session!");
                  session.logout();
               }
            });
         }
      };
      return testWrapper;
   }
}
