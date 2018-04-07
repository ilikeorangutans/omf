package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.MappedField;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.persistence.interceptor.handler.ItemHandler;
import org.om.core.api.persistence.request.ImmutablePersistenceRequest;
import org.om.core.api.persistence.request.Mode;
import org.om.core.api.persistence.request.PersistenceRequest;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.PersistenceResult;
import org.om.core.api.session.Session;
import org.om.core.impl.persistence.result.ImmutableCollectionPersistenceResult;
import org.om.core.impl.persistence.result.ImmutablePersistenceResult;
import org.om.core.impl.test.MappedFieldBuilder;

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
