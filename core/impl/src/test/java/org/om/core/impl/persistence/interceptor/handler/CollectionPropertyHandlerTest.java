package org.om.core.impl.persistence.interceptor.handler;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.om.core.api.annotation.MissingStrategy;
import org.om.core.api.exception.MissingException;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.persistence.PersistenceAdapter;
import org.om.core.api.session.Session;
import org.om.core.impl.mapping.ImmutableCollectionMapping;
import org.om.core.impl.mapping.ImmutableMappedField;

public class CollectionPropertyHandlerTest {

	@Test
	public void testWithEmptyResult() {
		Session session = null;
		CollectionHandler handler = new CollectionHandler(session);

		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(new ArrayList<String>());
		Collection<String> mapped = (Collection<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull,
				MissingException.class), adapter);

		assertThat(mapped, notNullValue());
		assertThat(mapped, instanceOf(List.class));

		assertThat(mapped.isEmpty(), is(true));
	}

	@Test
	public void testWithResultsPrimitiveType() {
		Session session = null;
		CollectionHandler handler = new CollectionHandler(session);

		final CollectionMapping mapping = new ImmutableCollectionMapping(List.class, String.class, "");
		List<String> inputList = new ArrayList<String>();
		inputList.add("one");
		PersistenceAdapter adapter = new TestingPassThroughPersistenceAdapter(inputList);
		Collection<String> mapped = (Collection<String>) handler.retrieve(new ImmutableMappedField("field", List.class, mapping, MissingStrategy.ReturnNull,
				MissingException.class), adapter);

		assertThat(mapped, notNullValue());
		assertThat(mapped, instanceOf(List.class));

		System.out.println("CollectionPropertyHandlerTest.testWithResultsPrimitiveType() " + mapped.size());
		for (String s : mapped) {
			System.out.println("CollectionPropertyHandlerTest.testWithResultsPrimitiveType() " + s);
		}

		assertThat(mapped.isEmpty(), is(true));
	}
}
