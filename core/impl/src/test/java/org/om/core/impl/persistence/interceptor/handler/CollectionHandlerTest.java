package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutableMappedField;

public class CollectionHandlerTest {

	@Test
	public void testWithEmptyResult() {
		Session session = null;
		CollectionHandler handler = new CollectionHandler(session);

		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(new ArrayList<String>());
		Collection<String> result = (Collection<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull,
				MissingException.class), adapter);

		assertThat(result, notNullValue());
		assertThat(result, instanceOf(List.class));
		assertThat(result.isEmpty(), is(true));
	}

	@Test
	public void testWithSimpleCollectionType() {
		Session session = null;
		CollectionHandler handler = new CollectionHandler(session);

		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		final List<String> input = new ArrayList<String>();
		input.add("I");
		input.add("like");
		input.add("cake");
		PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(input);
		List<String> result = (List<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull,
				MissingException.class), adapter);

		assertThat(result, notNullValue());
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(3));
	}
	
}
