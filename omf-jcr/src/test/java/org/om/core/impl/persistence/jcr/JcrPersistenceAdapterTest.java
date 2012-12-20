package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;
import javax.jcr.Node;

import org.apache.jackrabbit.commons.JcrUtils;
import org.junit.Rule;
import org.junit.Test;
import org.om.core.api.mapping.CollectionMapping;
import org.om.core.api.mapping.EntityMapping;
import org.om.core.api.mapping.extractor.EntityMappingExtractor;
import org.om.core.api.persistence.result.CollectionResult;
import org.om.core.api.persistence.result.MapResult;
import org.om.core.impl.entity.CollectionTestEntity;
import org.om.core.impl.entity.EntityWithMap;
import org.om.core.impl.persistence.jcr.test.TransientRepoTestEnv;

import com.google.guiceberry.junit4.GuiceBerryRule;

public class JcrPersistenceAdapterTest {

	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);

	@Inject
	private javax.jcr.Session jcrSession;

	@Inject
	private EntityMappingExtractor extractor;

	@Test
	public void testMapFromProperties() throws Exception {
		Node node = JcrUtils.getOrAddNode(jcrSession.getRootNode(), "foo");
		Node n = node.addNode("mapToProperties");
		n.setProperty("a", "first");
		n.setProperty("b", "second");
		n.setProperty("c", "third");

		EntityMapping entityMapping = extractor.extract(EntityWithMap.class);
		JcrPersistenceAdapter adapter = new JcrPersistenceAdapter(entityMapping, node);

		MapResult mapResult = adapter.getMapResult((CollectionMapping) entityMapping.getByFieldName("mapToProperties").getMapping());

		assertThat(mapResult, notNullValue());

		Map<String, String> map = (Map<String, String>) mapResult.getValue();
		assertThat(map, notNullValue());

		assertThat(map.size(), is(3));

		assertThat(map, hasKey("a"));
		assertThat(map, hasKey("b"));
		assertThat(map, hasKey("c"));

		assertThat(map.get("a"), is("first"));

	}

	@Test
	public void testGetCollection() throws Exception {
		Node node = JcrUtils.getOrAddNode(jcrSession.getRootNode(), "foo");
		node.addNode("a").setProperty("value", "value a");
		node.addNode("b").setProperty("value", "value b");
		node.addNode("c").setProperty("value", "value c");

		EntityMapping entityMapping = extractor.extract(CollectionTestEntity.class);
		JcrPersistenceAdapter adapter = new JcrPersistenceAdapter(entityMapping, node);

		CollectionResult result = adapter.getCollection((CollectionMapping) entityMapping.getByFieldName("listWithDifferingTargetAndImplType").getMapping());
		assertThat(result, notNullValue());
		Collection<?> collection = result.getValue();
		assertThat(collection, notNullValue());
		assertThat(collection.size(), is(3));

	}

}
