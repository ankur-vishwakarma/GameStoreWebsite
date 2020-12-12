package com.gamestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.Customer;
import com.gamestore.entity.Game;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review=new Review();
		Game game=new Game();
		
		game.setGameId(14);
		
		Customer customer=new Customer();
		customer.setCustomerId(2);
		
		review.setGame(game);
		review.setCustomer(customer);
		
		review.setHeadline("Lags a lot");
		review.setRating(3);
		review.setComment("Improve the game!");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId()>0);
	}
	
	@Test
	public void testGet() {
		Integer reviewId=1;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId=1;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Loved the Game! amazing.");
		
		Review updatedReview=reviewDao.update(review);
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
		
	}
	
	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
	    for(Review review : listReview) {
	    	System.out.println(review.getReviewId()+ " "+ review.getGame().getTitle() 
	    			+" " +review.getCustomer().getFirstname()+" "+review.getHeadline());
	    }
		
		assertTrue(listReview.size()>0);
	}
	
	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		
		System.out.println(totalReviews);
		
		assertTrue(totalReviews>0);
	}
	
	@Test
	public void testDeleteReview() {
		int reviewId =5;
		reviewDao.delete(reviewId);
		
		Review review=reviewDao.get(reviewId);
		
		assertNull(review);
	}
	
	@Test
	public void testFindByCustomerAndGameNotFound() {
		Integer customerId = 100;
		Integer gameId =99;
		
		Review result = reviewDao.findByCustomerAndGame(customerId, gameId);
		
		assertNull(result);
	}
	
	@Test
	public void testFindByCustomerAndGameFound() {
		Integer customerId = 3;
		Integer gameId =2;
		
		Review result = reviewDao.findByCustomerAndGame(customerId, gameId);
		
		assertNotNull(result);
	}
	
	@Test
	public void testListMostRecentReviews() {
		List<Review> recentReviews = reviewDao.listMostRecentReview();
		
		assertEquals(3, recentReviews.size());
	}

}
