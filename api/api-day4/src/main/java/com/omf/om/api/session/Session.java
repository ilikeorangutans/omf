package com.omf.om.api.session;

import com.day.cq.contentbus.Container;
import com.day.cq.contentbus.Page;

public interface Session {

	Object get(Class<?> clazz, Container container);

	Object get(Class<?> clazz, Page page);

}
