package org.om.core.impl.persistence.interceptor.handler;

import static org.mockito.Mockito.*;

import org.junit.*;
import org.om.core.api.annotation.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.session.*;
import org.om.core.impl.test.*;

public class ReferenceHandlerTest {
	/**
	 * Tests that the {@link ReferenceHandler} requests the correct implementation type from the
	 * session.
	 */
	@Test
	public void test() {
		final Session session = mock(Session.class);
		final PersistenceAdapter adapter = mock(PersistenceAdapter.class);
		when(adapter.resolve("/foo/bar")).thenReturn("/resolved/foo/bar");
		final MappedField mappedField = new MappedFieldBuilder().withName("field").withType(EntityImplementingInterface.class)
				.withReferenceMapping("field", MyInterface.class, EntityImplementingInterface.class, "/foo/bar", LookupMode.Direct).create();
		final ReferenceHandler handler = new ReferenceHandler(session);
		handler.retrieve(mappedField, adapter);
		verify(session).get(EntityImplementingInterface.class, "/resolved/foo/bar");
	}
}
