package org.om.core.impl.test;

import java.util.*;

import org.om.core.api.annotation.*;
import org.om.core.api.annotation.Collection;

/**
 * An entity that exposes various different mappings for collections.
 *
 * @author Jakob Külzer
 */
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
	@Collection(targetType = MyInterface.class)
	@Mapped(implementationType = EntityImplementingInterface.class)
	private List<MyInterface> collectionWithDifferentTargetAndImplType;
	@Collection(targetType = EntityWithPrimitiveProperties.class)
	private Map<String, EntityWithPrimitiveProperties> map;

	public List<Integer> getCollectionWithIntegers() {
		return collectionWithIntegers;
	}

	public List<String> getCollectionWithStrings() {
		return collectionWithStrings;
	}

	public String getId() {
		return id;
	}
}
