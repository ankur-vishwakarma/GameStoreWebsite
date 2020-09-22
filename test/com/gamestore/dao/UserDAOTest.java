package com.gamestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.users;

public class UserDAOTest {

	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupclass() throws Exception {
		userDAO=new UserDAO();
	}
	
	@Test
	public void testCreateUsers() {
		users user1=new users(); 
		user1.setEmail("kruskals.com");
		user1.setFullName("krus");
		user1.setPassword("spanning");
		 
		user1=userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testUpdateUsers() {
		users user =new users();
		user.setUserId(1);
		user.setEmail("name@codejava.net");
		user.setFullName("ANkur");
		user.setPassword("mysecret");
		
		user=userDAO.update(user);
		String expected ="mysecret";
		String actual=user.getPassword();
		System.out.println(user.getPassword());
		assertEquals(expected,actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId=1;
		users user= userDAO.get(userId);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		users user= userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId=16;
		userDAO.delete(userId);
			
		users user= userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected= Exception.class)
	public void testDeleteNonExistUsers() {
		Integer userId=99;
		userDAO.delete(userId);
	}
	
	@Test
	public void tsetListAll() {
		List<users> listUsers=userDAO.listAll();
		assertTrue(listUsers.size() > 0 );
	}
	
	@Test
	public void testCount() {
		long totalUsers=userDAO.count();
		assertTrue(totalUsers>0);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="name@codejava.net";
		String password="mysecret";
		
		boolean loginResult=userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email="name@codejav.net";
		String password="mysecret";
		
		boolean loginResult=userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	@Test
	public void testFindByEmail() {
		String email="name@codejava.net";
		users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}
	
}
