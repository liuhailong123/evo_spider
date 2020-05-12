package com.frameworks.core.shiro;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class ShiroSessionDAO extends AbstractSessionDAO {

	@Override
	public void delete(Session session) {
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return null;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return null;
	}

}
