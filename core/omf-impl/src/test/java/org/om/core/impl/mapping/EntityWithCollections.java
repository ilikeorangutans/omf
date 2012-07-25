package org.om.core.impl.mapping;

import java.util.List;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;

@Entity
public class EntityWithCollections {

	@Id
	private String id;

	@Collection(targetType = String.class)
	private List<String> collectionWithStrings;

	@Collection(targetType = Integer.class)
	private List<Integer> collectionWithIntegers;

	@Collection(targetType = EntityWithPrimitiveProperties.class)
	private List<EntityWithPrimitiveProperties> collectionWithReferenceTypes;

	public List<String> getCollectionWithStrings() {
		return collectionWithStrings;
	}

	public String getId() {
		return id;
	}

	public List<Integer> getCollectionWithIntegers() {
		return collectionWithIntegers;
	}
}
