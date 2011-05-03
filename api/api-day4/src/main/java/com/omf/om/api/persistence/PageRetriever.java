package com.omf.om.api.persistence;

import com.day.cq.contentbus.Page;
import com.omf.om.api.Path;

public interface PageRetriever {

	public boolean hasPage(Path path);

	public Page getPage(Path path);

}
