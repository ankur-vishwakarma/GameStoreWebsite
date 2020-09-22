package com.gamestore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.gamestore.entity.users;

public class UserDAO extends JpaDAO<users> implements GenericDAO<users> {

	public UserDAO() {
	}

	public users create(users user) {
		return super.create(user);
	}
	@Override
	public users update(users user) {
		return super.update(user);
	}

	@Override
	public users get(Object userId) {
		return super.find(users.class, userId);
	}

	public users findByEmail(String email) {
		List<users> listUsers =super.findWithNamedQuery("Users.findByEmail", "email", email);
		
		if(listUsers != null && listUsers.size()==1) {
			return listUsers.get(0);
		}
		
		return null;
	}
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
		
		if(listUsers.size()==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void delete(Object userId) {
		super.delete(users.class,userId);
	}

	@Override
	public List<users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

}
