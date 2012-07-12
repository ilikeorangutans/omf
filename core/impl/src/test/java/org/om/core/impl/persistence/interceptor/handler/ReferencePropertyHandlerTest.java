package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.ImmutableMappedField;
import org.om.core.impl.mapping.field.ImmutableReferenceMapping;

@RunWith(JMock.class)
public class ReferencePropertyHandlerTest {

	private Session session;

	private Mockery mockery = new JUnit4Mockery();

	@Before
	public void setUp() {
		session = mockery.mock(Session.class);
	}

	@Test
	@Ignore
	public void test() {

		mockery.checking(new Expectations() {
			{
				oneOf(session).get(EntityWithPrimitiveProperties.class, "/foo/bar");
			}
		});

		ReferenceHandler handler = new ReferenceHandler(session);

		PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("/foo/bar");
		MappedField mappedField = new ImmutableMappedField("fieldname", EntityWithPrimitiveProperties.class, new ImmutableReferenceMapping(
				EntityWithPrimitiveProperties.class, "path"), MissingStrategy.ReturnNull, MissingException.class);
		Object retrieve = handler.retrieve(mappedField, delegate);

		assertThat(retrieve, notNullValue());

		System.out.println("ReferencePropertyHandlerTest.test() " + retrieve);

		fail("implement me");
	}
}
