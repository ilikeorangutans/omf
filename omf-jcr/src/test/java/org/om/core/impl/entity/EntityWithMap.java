package org.om.core.impl.entity;

import java.util.*;

import org.om.core.api.annotation.*;
import org.om.core.api.annotation.Collection;

@Entity
public class EntityWithMap {
	@Id
	private String id;
	@Collection(targetType = String.class, mode = CollectionMode.Properties)
	private Map<String, String> mapToProperties;

	public EntityWithMap() {
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getMapToProperties() {
		return mapToProperties;
	}
}
