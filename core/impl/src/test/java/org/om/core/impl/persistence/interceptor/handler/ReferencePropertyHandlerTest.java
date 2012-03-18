package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.annotation.PropertyNameStrategy;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.EntityWithPrimitiveProperties;
import org.om.core.impl.mapping.ImmutablePropertyMapping;

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

		ReferencePropertyHandler handler = new ReferencePropertyHandler(session);

		Object retrieve = handler.retrieve(new ImmutablePropertyMapping("fieldname", false, PropertyNameStrategy.FieldName, "fieldname",
				EntityWithPrimitiveProperties.class, null, PropertyMissingStrategy.ReturnNull, null), "/foo/bar");

		assertThat(retrieve, notNullValue());
	}
}
