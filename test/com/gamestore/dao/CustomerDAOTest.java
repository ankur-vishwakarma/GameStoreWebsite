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
		customer.setEmail("idk@yahoo.com");
		customer.setFirstname("Dont");
		customer.setLastname("Know");
		customer.setCity("Anycity");
		customer.setState("Anystate");
		customer.setCountry("Anycountry");
		customer.setAddressLine1("random");
		customer.setAddressLine2("Line 2");
		customer.setPassword("rand()");
		customer.setPhone("12345678777");
		customer.setCustomercol("678");
		
		Customer savedCustomer = customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 7;
		Customer customer=customerDao.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer=customerDao.get(7);
		String firstName="iDont";
		customer.setFirstname(firstName);
		customer.setPassword("newone");
		
		Customer updatedCustomer = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFirstname().equals(firstName));
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 7;
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
		
		assertEquals(6, totalCustomers);
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
