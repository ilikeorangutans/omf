package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.om.core.api.annotation.PropertyMissingStrategy;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.ImmutableCollectionMapping;

public class CollectionPropertyHandlerTest {
   @Test
   public void testWithEmptyResult() {
      Session session = null;
      CollectionPropertyHandler handler = new CollectionPropertyHandler(session);
      final CollectionMapping mapping = new ImmutableCollectionMapping("field", List.class, String.class, "", PropertyMissingStrategy.DefaultValue, null);
      PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(new ArrayList<String>());
      Collection<String> mapped = (Collection<String>) handler.retrieve(mapping, adapter);
      assertThat(mapped, notNullValue());
      assertThat(mapped, instanceOf(List.class));
      assertThat(mapped.isEmpty(), is(true));
   }

   @Test
   @Ignore
   public void testWithResultsPrimitiveType() {
      Session session = null;
      CollectionPropertyHandler handler = new CollectionPropertyHandler(session);
      final CollectionMapping mapping = new ImmutableCollectionMapping("field", List.class, String.class, "", PropertyMissingStrategy.DefaultValue, null);
      List<String> inputList = new ArrayList<String>();
      inputList.add("one");
      PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(inputList);
      Collection<String> mapped = (Collection<String>) handler.retrieve(mapping, adapter);
      assertThat(mapped, notNullValue());
      assertThat(mapped, instanceOf(List.class));
      assertThat(mapped.isEmpty(), is(true));
   }
}
