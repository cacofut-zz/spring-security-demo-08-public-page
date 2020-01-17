package br.com.salao.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.salao.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public User findByUserName(String userName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// now retrieve/red from database using username
		Query<User> query = currentSession.createQuery("FROM User WHERE username = :uName", User.class);
		query.setParameter("uName", userName);
		User aux_user = null;
		try {
			aux_user = query.getSingleResult();
		}
		catch(Exception ex) {
			aux_user = null;
		}
		return aux_user;
	}

	
}
