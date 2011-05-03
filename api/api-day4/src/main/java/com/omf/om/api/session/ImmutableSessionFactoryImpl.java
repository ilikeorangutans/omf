package com.omf.om.api.session;

import com.day.cq.contentbus.Ticket;

public class ImmutableSessionFactoryImpl implements SessionFactory {

	public ImmutableSessionFactoryImpl() {

	}

	public Session createSession(Ticket ticket) {
		return new ImmutableSessionImpl();
	}

}
