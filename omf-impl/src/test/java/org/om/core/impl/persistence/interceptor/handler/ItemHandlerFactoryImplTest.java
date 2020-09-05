package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.persistence.interceptor.handler.*;
import org.om.core.api.persistence.request.*;
import org.om.core.api.persistence.result.*;
import org.om.core.api.session.*;
import org.om.core.impl.persistence.result.*;
import org.om.core.impl.test.*;

public class ItemHandlerFactoryImplTest {
	ItemHandlerFactoryImpl factory = new ItemHandlerFactoryImpl();
	Session session = mock(Session.class);
	PersistenceAdapter adapter = mock(PersistenceAdapter.class);

	@Test
	public void testCollectionHandler() {
		final MappedField field = new MappedFieldBuilder().withName("collection").withType(List.class).withCollectionMapping(List.class, String.class, "collection").create();
		final ItemHandler itemHandler = factory.get(session, field);
		assertThat(itemHandler, notNullValue());
		new ImmutablePersistenceRequest("primitive", String.class, Mode.Relative);
		final CollectionMapping collectionMapping = (CollectionMapping) field.getMapping();
		final Collection<?> result = new ArrayList<String>();
		final CollectionResult value = new ImmutableCollectionPersistenceResult(result);
		when(adapter.getCollection(collectionMapping)).thenReturn(value);
		final Object retrieve = itemHandler.retrieve(field, adapter);
		assertThat(retrieve, notNullValue());
		assertThat(retrieve, instanceOf(List.class));
	}

	@Test
	public void testIdHandler() {
		final MappedField field = new MappedFieldBuilder().withName("id").withType(String.class).withIdMapping().create();
		final ItemHandler itemHandler = factory.get(session, field);
		assertThat(itemHandler, notNullValue());
		when(adapter.getId()).thenReturn("/some/id");
		final Object retrieve = itemHandler.retrieve(field, adapter);
		assertThat(retrieve, notNullValue());
		assertEquals("/some/id", retrieve);
	}

	@Test
	public void testPrimitiveHandler() {
		final MappedField field = new MappedFieldBuilder().withName("primitive").withType(String.class).withPropertyMapping("primitive", String.class).create();
		final ItemHandler itemHandler = factory.get(session, field);
		assertThat(itemHandler, notNullValue());
		final PersistenceRequest request = new ImmutablePersistenceRequest("primitive", String.class, Mode.Relative);
		final PersistenceResult value = new ImmutablePersistenceResult("result");
		when(adapter.getProperty(request)).thenReturn(value);
		final Object retrieve = itemHandler.retrieve(field, adapter);
		assertThat(retrieve, notNullValue());
		assertEquals("result", retrieve);
	}
}
