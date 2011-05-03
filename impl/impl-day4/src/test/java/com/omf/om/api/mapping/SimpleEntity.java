package com.omf.om.api.mapping;

import com.omf.om.annotation.Atom;
import com.omf.om.annotation.Entity;

@Entity
public class SimpleEntity {

	@Atom
	private String field;

	@Atom(name = "differentAtom")
	private String fieldWithDifferentAtom;

	@Atom(name = "differentAtom", container = "container")
	private String fieldWithDifferentAtomAndContainer;

	public SimpleEntity() {
	}

}
