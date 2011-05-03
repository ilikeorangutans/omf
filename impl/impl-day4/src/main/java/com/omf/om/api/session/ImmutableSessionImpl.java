package com.omf.om.api.session;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import com.day.cq.contentbus.Container;
import com.day.cq.contentbus.Page;
import com.omf.om.api.Path;
import com.omf.om.api.mapping.EntityMapping;
import com.omf.om.api.mapping.MappingFactory;
import com.omf.om.api.persistence.PageRetriever;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutableSessionImpl implements Session {

	private final PageRetriever pageRetriever;
	private final MappingFactory mappingFactory;

	public ImmutableSessionImpl(PageRetriever pageRetriever,
			MappingFactory mappingFactory) {
		this.pageRetriever = pageRetriever;
		this.mappingFactory = mappingFactory;
	}

	public Object get(Class<?> clazz, Container container) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(Class<?> clazz, Page page) {
		EntityMapping mapping = mappingFactory.getMapping(clazz);

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		Callback callback = null;
		enhancer.setCallback(callback);
		Object object = enhancer.create();

		return object;
	}

	public Object get(Class<?> clazz, String path) {
		return get(clazz, pageRetriever.getPage(new Path(path)));
	}
}
