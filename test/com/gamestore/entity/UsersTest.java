package com.gamestore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gamestore.entity.users;

public class UsersTest {

	public static void main(String[] args) {
		users user1=new users(); 
		user1.setEmail("nikki@gmail.com");
		user1.setFullName("nikita");
		user1.setPassword("hwll");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GameStoreWebsite");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("a user object was persisted");
	}

}
