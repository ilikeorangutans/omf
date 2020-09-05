package org.om.core.impl.persistence.interceptor.handler.collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;
import java.util.Collection;

import org.junit.*;
import org.om.core.api.annotation.*;
import org.om.core.api.exception.*;
import org.om.core.api.mapping.*;
import org.om.core.api.persistence.*;
import org.om.core.api.session.*;
import org.om.core.impl.mapping.*;
import org.om.core.impl.persistence.interceptor.handler.*;

public class PrimitiveListHandlerTest {
	@Test
	public void testWithEmptyResult() {
		final Session session = null;
		final AbstractCollectionHandler handler = new PrimitiveListHandler(session);
		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		final PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(new ArrayList<String>());
		final Collection<String> result = (Collection<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull, MissingException.class), adapter);
		assertThat(result, notNullValue());
		assertThat(result, instanceOf(List.class));
		assertThat(result.isEmpty(), is(true));
	}

	@Test
	public void testWithSimpleCollectionType() {
		final Session session = null;
		final AbstractCollectionHandler handler = new PrimitiveListHandler(session);
		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		final List<String> input = new ArrayList<String>();
		input.add("I");
		input.add("like");
		input.add("cake");
		final PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(input);
		final List<String> result = (List<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull, MissingException.class), adapter);
		assertThat(result, notNullValue());
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(3));
	}
}
