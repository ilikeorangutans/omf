package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.*;
import org.junit.*;
import org.junit.runner.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.session.*;
import org.om.core.impl.test.*;

@RunWith(JMock.class)
public class ReferencePropertyHandlerTest {
	private Session session;
	private final Mockery mockery = new JUnit4Mockery();

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
		final ReferenceHandler handler = new ReferenceHandler(session);
		final PersistenceAdapter delegate = new TestingPassThroughPersistenceAdapter("/foo/bar");
		final MappedField mappedField = new MappedFieldBuilder().withName("mappedField").withType(EntityWithPrimitiveProperties.class)
				.withReferenceMapping("fieldname", EntityWithPrimitiveProperties.class, "path").create();
		final Object retrieve = handler.retrieve(mappedField, delegate);
		assertThat(retrieve, notNullValue());
		System.out.println("ReferencePropertyHandlerTest.test() " + retrieve);
		fail("implement me");
	}
}
