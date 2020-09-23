package com.gamestore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.Game;

public class ShoppingCartTest {
	public static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart=new ShoppingCart();
		
		Game game=new Game(1);
		game.setPrice(100);
		
		cart.addItem(game);
		cart.addItem(game);
	}
	
	@Test
	public void testAddItem() {
		
		Map<Game, Integer> items = cart.getItems();
		int quantity=items.get(new Game(1));
		
		assertEquals(2, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Game(1));
		
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Game game2=new Game(2);
		cart.addItem(game2);
		
		cart.removeItem(new Game(2));
		
		assertNull(cart.getItems().get(game2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Game game3=new Game(3);
		cart.addItem(game3);
		cart.addItem(game3);
		cart.addItem(game3);
		
		assertEquals(5, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount1() {
		ShoppingCart cart=new ShoppingCart();
		assertEquals(0.0f, cart.getTotalAmount(),0.0f);
	}
	
	@Test
	public void testGetTotalAmount2() {
		assertEquals(200.0f, cart.getTotalAmount(),0.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}

}
