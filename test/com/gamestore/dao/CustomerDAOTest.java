package com.gamestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("anyonemalik@gmail.com");
		customer.setFullname("Anyone Malik");
		customer.setCity("Anycity");
		customer.setCountry("Anycountry");
		customer.setAddress("random");
		customer.setPassword("rand()");
		customer.setPhone("12345678777");
		customer.setCustomercol("678");
		
		Customer savedCustomer = customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 1;
		Customer customer=customerDao.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer=customerDao.get(1);
		String fullName="Random Singhji";
		customer.setFullname(fullName);
		
		Customer updatedCustomer = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullName));
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 1;
		customerDao.delete(customerId);
		Customer customer=customerDao.get(customerId);
		
		assertNull(customer);
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomers= customerDao.count();
		
		assertEquals(2, totalCustomers);
	}

	@Test
	public void testFindByEmail() {
		String email="randomsingh@gmail.com";
		Customer customer=customerDao.findByEmail(email);
		
		assertNotNull(customer);
		
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="pisu@gmail.com";
		String password="aaa";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email="pisunotexist@gmail.com";
		String password="aaa";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNull(customer);
	}
}
