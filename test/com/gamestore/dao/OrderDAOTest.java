package com.gamestore.dao;

import static org.junit.Assert.*;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.Customer;
import com.gamestore.entity.Game;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.OrderDetail;
import com.gamestore.entity.OrderDetailId;

public class OrderDAOTest {

	private static OrderDAO orderDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateGameOrder() {
		GameOrder order=new GameOrder();
		Customer customer=new Customer();
		customer.setCustomerId(2);
		
		order.setCustomer(customer);
		order.setFirstname("Ankit");
		order.setLastname("vis");
		order.setPhone("78372837430");
		order.setAddressLine1("H-38 Gujaini");
		order.setAddressLine2("kanpur");
		order.setCity("knp");
		order.setState("UP");
		order.setCountry("IN");
		order.setPaymentMethod("paypal");
		order.setZipcode("282763");
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		OrderDetail orderDetail=new OrderDetail();
		
		Game game =new Game(2);
		orderDetail.setGame(game);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(40.0f);
		orderDetail.setGameOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		order.setTax(4.0f);
		order.setShippingFee(6.0f);
		order.setSubtotal(44.0f);
		order.setTotal(50.0f);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId()>0);
	}
	
	@Test
	public void testCreateGameOrder2() {
		GameOrder order=new GameOrder();
		Customer customer=new Customer();
		customer.setCustomerId(3);
		
		order.setCustomer(customer);
		order.setFirstname("Atul Katiyar");
		order.setPhone("1226527637");
		order.setAddressLine1("H-38 Gujaini Kanpur");
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		
		OrderDetail orderDetail1=new OrderDetail();
		
		Game game1 =new Game(17);
		orderDetail1.setGame(game1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(40.0f);
		orderDetail1.setGameOrder(order);
		
		orderDetails.add(orderDetail1);
		
		OrderDetail orderDetail2=new OrderDetail();
		Game game2 =new Game(15);
		orderDetail2.setGame(game2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(15.0f);
		orderDetail2.setGameOrder(order);
		
		orderDetails.add(orderDetail2);
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId()>0 && order.getOrderDetails().size()==2);
	}

	@Test
	public void testUpdateGameOrderShippingAddress() {
		Integer orderId=2;
		GameOrder order=orderDAO.get(orderId);
		order.setAddressLine1("New Address test");
		
		orderDAO.update(order);
		
		GameOrder updatedOrder= orderDAO.get(orderId);
		
		assertEquals(order.getAddressLine1(), updatedOrder.getAddressLine1());
	}
	
	@Test
	public void testUpdateGameOrderDetail() {
		Integer orderId=9;
		GameOrder order=orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator=order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail= iterator.next();
			if(orderDetail.getGame().getGameId()==2) {
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(60);
			}
		}
		
		orderDAO.update(order);
		
		GameOrder updatedOrder= orderDAO.get(orderId);
		
		iterator=order.getOrderDetails().iterator();
		
		int expectedQuantity=3;
		float expectedSubtotal=60;
		int actualQuantity=0;
		float actualSubtotal=0;
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail= iterator.next();
			if(orderDetail.getGame().getGameId()==2) {
				actualQuantity=orderDetail.getQuantity();
				actualSubtotal=orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity,actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}

	@Test
	public void testGet() {
		Integer orderId=9;
		GameOrder order=orderDAO.get(orderId);
		System.out.println(order.getFirstname());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void testDeleteObject() {
		int orderId=4;
		orderDAO.delete(orderId);
		
		GameOrder order=orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<GameOrder> listOrders =orderDAO.listAll();
		
		assertTrue(listOrders.size()>0);
	}

	@Test
	public void testCount() {
		long totalOrders=orderDAO.count();
		
		assertEquals(3, totalOrders);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId =99;
		List <GameOrder> listOrders=orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId =4;
		List <GameOrder> listOrders=orderDAO.listByCustomer(customerId);
		  
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId=10;
		Integer customerId =99;
		
		GameOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId=5;
		Integer customerId =3;
		
		GameOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<GameOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
	}
}
