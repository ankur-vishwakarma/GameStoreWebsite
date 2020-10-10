package com.gamestore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gamestore.entity.Game;

public class ShoppingCart {
	private Map<Game, Integer> cart= new HashMap<>();
	
	public void addItem(Game game) {
		if(cart.containsKey(game)) {
			cart.put(game, cart.get(game)+1);
		}else {
			cart.put(game, 1);
		}
	}
	
	public void removeItem(Game game) {
		cart.remove(game);
	}
	
	public int getTotalQuantity() {
		int total=0;
		
		Iterator <Game> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Game next=iterator.next();
			Integer quantity=cart.get(next);
			total+=quantity;
		}
		
		return total;
	}
	
	public float getTotalAmount() {
		float total=0.0f;
		
		Iterator <Game> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Game game=iterator.next();
			Integer quantity=cart.get(game);
			double subTotal=quantity * game.getPrice();
			total+=subTotal;
		}
		
		return total;
	}
	
	public void updateCart(int[] gameIds,int[] quantities) {
		for(int i=0;i<gameIds.length;i++) {
			Game key=new Game(gameIds[i]);
			Integer value =quantities[i];
			cart.put(key, value);
		}
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public Map<Game, Integer> getItems(){
		return this.cart;
	}
}
