package com.technosmart.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

	@Autowired
	SessionFactory sessionFactory;
	
	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}
	
}
