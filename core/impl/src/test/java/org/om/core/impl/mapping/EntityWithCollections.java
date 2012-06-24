package org.om.core.impl.mapping;

import java.util.List;

import org.om.core.api.annotation.Collection;
import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

@Entity
public class EntityWithCollections {

	@Id
	@Property
	private String id;

	@Property
	@Collection(targetType = String.class)
	private List<String> collectionWithStrings;

	@Property
	@Collection(targetType = Integer.class)
	private List<Integer> collectionWithIntegers;

	@Property
	@Collection(targetType = EntityWithPrimitiveProperties.class)
	List<EntityWithPrimitiveProperties> collectionWithReferenceTypes;

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
