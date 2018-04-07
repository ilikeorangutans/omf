package org.om.core.impl.persistence.jcr;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class JcrPersistenceDelegateFactoryTest {
   @Rule
   public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);

   @Test
   @Ignore
   public void test() {
      new JcrPersistenceAdapterFactory();
      fail("Not yet implemented");
   }
}
