package com.gamestore.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gamestore.entity.Game;

public class GameDAO extends JpaDAO<Game> implements GenericDAO<Game> {


	public GameDAO() {
	}
	
	@Override
	public Game create(Game game) {
		game.setLastUpdateTime(new Date());
		return super.create(game);
	}

	@Override
	public Game update(Game game) {
		game.setLastUpdateTime(new Date());
		return super.update(game);
	}

	@Override
	public Game find(Class<Game> type, Object id) {
		// TODO Auto-generated method stub
		return super.find(type, id);
	}

	@Override
	public void delete(Class<Game> type, Object id) {
		// TODO Auto-generated method stub
		super.delete(type, id);
	}

	@Override
	public List<Game> findWithNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName);
	}

	@Override
	public List<Game> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName, paramName, paramValue);
	}

	@Override
	public List<Game> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public long countWithNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery(queryName);
	}

	@Override
	public Game get(Object gameId) {
		return super.find(Game.class, gameId);
	}

	@Override
	public void delete(Object gameId) {
		super.delete(Game.class, gameId);
	}

	@Override
	public List<Game> listAll() {
		return super.findWithNamedQuery("Game.findAll");
	}

	public Game findByTitle(String title) {
		List<Game> result = super.findWithNamedQuery("Game.findByTitle", "title", title);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public List<Game> listByCategory(int categoryId){
		return super.findWithNamedQuery("Game.findByCategory", "catId", categoryId);
	}
	
	public List<Game> listNewGames(){
		return super.findWithNamedQuery("Game.listNew", 0,4);
	}
	
	public List<Game> search(String keyword){
		return super.findWithNamedQuery("Game.search", "keyword", keyword);
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("Game.countAll");
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Game.countByCategory", "catId", categoryId);
	}

}
