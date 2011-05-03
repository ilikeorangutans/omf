package com.omf.om.api.session;

import com.day.cq.contentbus.Ticket;

public interface SessionFactory {

	/**
	 * Creates a new {@link Session} using the given {@link Ticket}.
	 * 
	 * @param ticket
	 * @return
	 */
	public Session createSession(Ticket ticket);

}
