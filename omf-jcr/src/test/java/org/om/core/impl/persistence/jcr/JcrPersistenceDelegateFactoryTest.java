package org.om.core.impl.persistence.jcr;

import static org.junit.Assert.*;

import org.junit.*;
import org.om.core.impl.persistence.jcr.test.*;

import com.google.guiceberry.junit4.*;

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
