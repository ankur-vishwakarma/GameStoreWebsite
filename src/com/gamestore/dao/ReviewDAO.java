package com.gamestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamestore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}

	//WE WONT OVERRIDE UPDATE AS ITS ALREADY IMPLEMENTED IN JPADAO AND NO CHANGES ARE REQUIRED
	// AS IN CREATE WE NEEDED TO ADD DATE SO WE OVERRIDE BUT UPDATE IS SAME FOR ALL
	
	
	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public Review findByCustomerAndGame(Integer customerId,Integer gameId) {
		Map<String, Object> parameters= new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("gameId", gameId);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndGame" , parameters);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Review> listMostRecentReview(){
		return super.findWithNamedQuery("Review.listAll", 0, 3);
	}

}
