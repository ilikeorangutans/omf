package com.omf.om.api.session;

import com.day.cq.contentbus.Ticket;
import com.omf.om.api.mapping.MappingFactory;
import com.omf.om.api.mapping.OnDemandMappingFactory;
import com.omf.om.api.persistence.PageRetriever;
import com.omf.om.api.persistence.PageRetrieverImpl;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public class ImmutableSessionFactoryImpl implements SessionFactory {

	private final MappingFactory mappingFactory;

	public ImmutableSessionFactoryImpl() {
		mappingFactory = new OnDemandMappingFactory();
	}

	public Session createSession(Ticket ticket) {
		PageRetriever pageRetriever = new PageRetrieverImpl(ticket);
		return new ImmutableSessionImpl(pageRetriever, mappingFactory);
	}

}
