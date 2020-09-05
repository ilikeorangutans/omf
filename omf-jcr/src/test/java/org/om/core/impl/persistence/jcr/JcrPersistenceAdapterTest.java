package org.om.core.impl.persistence.jcr;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import javax.inject.*;
import javax.jcr.*;

import org.apache.jackrabbit.commons.*;
import org.junit.*;
import org.om.core.api.mapping.*;
import org.om.core.api.mapping.extractor.*;
import org.om.core.api.persistence.result.*;
import org.om.core.impl.entity.*;
import org.om.core.impl.persistence.jcr.test.*;

import com.google.guiceberry.junit4.*;

public class JcrPersistenceAdapterTest {
	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(TransientRepoTestEnv.class);
	@Inject
	private javax.jcr.Session jcrSession;
	@Inject
	private EntityMappingExtractor extractor;

	@Test
	public void testGetCollection() throws Exception {
		final Node node = JcrUtils.getOrAddNode(jcrSession.getRootNode(), "foo");
		node.addNode("a").setProperty("value", "value a");
		node.addNode("b").setProperty("value", "value b");
		node.addNode("c").setProperty("value", "value c");
		final EntityMapping entityMapping = extractor.extract(CollectionTestEntity.class);
		final JcrPersistenceAdapter adapter = new JcrPersistenceAdapter(entityMapping, node);
		final CollectionResult result = adapter.getCollection((CollectionMapping) entityMapping.getByFieldName("listWithDifferingTargetAndImplType").getMapping());
		assertThat(result, notNullValue());
		final Collection<?> collection = result.getValue();
		assertThat(collection, notNullValue());
		assertThat(collection.size(), is(3));
	}

	@Test
	public void testMapFromProperties() throws Exception {
		final Node node = JcrUtils.getOrAddNode(jcrSession.getRootNode(), "foo");
		final Node n = node.addNode("mapToProperties");
		n.setProperty("a", "first");
		n.setProperty("b", "second");
		n.setProperty("c", "third");
		final EntityMapping entityMapping = extractor.extract(EntityWithMap.class);
		final JcrPersistenceAdapter adapter = new JcrPersistenceAdapter(entityMapping, node);
		final MapResult mapResult = adapter.getMapResult((CollectionMapping) entityMapping.getByFieldName("mapToProperties").getMapping());
		assertThat(mapResult, notNullValue());
		final Map<String, String> map = (Map<String, String>) mapResult.getValue();
		assertThat(map, notNullValue());
		assertThat(map.size(), is(3));
		assertThat(map, hasKey("a"));
		assertThat(map, hasKey("b"));
		assertThat(map, hasKey("c"));
		assertThat(map.get("a"), is("first"));
	}
}
