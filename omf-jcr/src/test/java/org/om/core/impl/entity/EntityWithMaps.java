package org.om.core.impl.entity;

import java.util.*;

import org.om.core.api.annotation.*;
import org.om.core.api.annotation.Collection;

@Entity
public class EntityWithMaps {
	@Id
	private String id;
	@Collection(mode = CollectionMode.Properties, location = "mapFromProperties", targetType = String.class)
	private Map<String, String> map;

	public EntityWithMaps() {
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getMap() {
		return map;
	}
}
