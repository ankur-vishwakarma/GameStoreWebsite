package com.gamestore.dao;

import java.util.Date;
import java.util.List;

import com.gamestore.entity.GameOrder;

public class OrderDAO extends JpaDAO<GameOrder> implements GenericDAO<GameOrder> {

	@Override
	public GameOrder create(GameOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		return super.create(order);
	}

	@Override
	public GameOrder update(GameOrder order) {
		return super.update(order);
	}

	@Override
	public GameOrder get(Object orderId) {
		return super.find(GameOrder.class, orderId);
	}

	@Override
	public void delete(Object orderId) {
		super.delete(GameOrder.class, orderId);
	}

	@Override
	public List<GameOrder> listAll() {
		return super.findWithNamedQuery("GameOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("GameOrder.countAll");
	}

}
