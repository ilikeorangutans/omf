package org.om.core.impl.persistence.jcr;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class JcrPersistenceDelegateFactoryTest {
	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);

	@Test
	@Ignore
	public void test() {

		final JcrPersistenceDelegateFactory factory = new JcrPersistenceDelegateFactory();

		fail("Not yet implemented");
	}

}
