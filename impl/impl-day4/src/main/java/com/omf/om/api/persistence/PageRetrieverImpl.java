package com.omf.om.api.persistence;

import com.day.cq.contentbus.ContentBusException;
import com.day.cq.contentbus.NoSuchPageException;
import com.day.cq.contentbus.Page;
import com.day.cq.contentbus.Ticket;
import com.omf.om.api.Path;
import com.omf.om.api.exception.PathNotFoundException;

public class PageRetrieverImpl implements PageRetriever {

	private final Ticket ticket;

	public PageRetrieverImpl(Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean hasPage(Path path) {
		return ticket.hasPage(path.toString());
	}

	public Page getPage(Path path) {
		try {
			return ticket.getPage(path.toString());
		} catch (NoSuchPageException e) {
			throw new PathNotFoundException(path);
		} catch (ContentBusException e) {
			throw new RuntimeException(path.toString(), e);
		}
	}

}
