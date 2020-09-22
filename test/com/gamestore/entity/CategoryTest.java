package com.gamestore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gamestore.entity.users;

public class CategoryTest {

	public static void main(String[] args) {
		Category newcat= new Category("Simulation");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GameStoreWebsite");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(newcat);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("a category object was persisted");
	}

}
