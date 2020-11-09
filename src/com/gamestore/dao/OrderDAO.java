package com.gamestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public GameOrder get(Integer orderId,Integer customerId) {
		Map<String, Object> parameters=new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		
		List <GameOrder> result=super.findWithNamedQuery("GameOrder.findByIdAndCustomer", parameters);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
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

	public List<GameOrder> listByCustomer(Integer customerId) {
		return super.findWithNamedQuery("GameOrder.findByCustomer", 
				"customerId", customerId);
	}

}
