package org.om.core.impl.persistence.interceptor.handler;

import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;

@RunWith(JMock.class)
public class ReferencePropertyHandlerTest {

	private Session session;

	private Mockery mockery = new JUnit4Mockery();

	@Before
	public void setUp() {
		session = mockery.mock(Session.class);
	}

	@Test
	public void test() {

		mockery.checking(new Expectations() {
			{
				oneOf(session).get(EntityWithPrimitiveProperties.class, "/foo/bar");
			}
		});

		ReferenceHandler handler = new ReferenceHandler(session);

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("/foo/bar");
		// Object retrieve = handler.retrieve(new
		// ImmutablePropertyMapping("fieldname", false, "fieldname",
		// EntityWithPrimitiveProperties.class, null,
		// MissingStrategy.ReturnNull, null), delegate);
		//
		// assertThat(retrieve, notNullValue());
		fail("implement me");
	}
}
