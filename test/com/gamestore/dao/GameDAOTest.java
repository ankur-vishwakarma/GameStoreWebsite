package com.gamestore.dao;

import static org.junit.Assert.*;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gamestore.entity.Category;
import com.gamestore.entity.Game;

public class GameDAOTest {

	private static GameDAO gameDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameDao = new GameDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gameDao.close();
	}

	@Test
	public void testCreateGame() throws ParseException, IOException {
		Game newGame = new Game();
		
		Category category=new Category("Arcade");
		category.setCategoryId(1);
		newGame.setCategory(category);
		
		newGame.setTitle("Donkey Kong");
		newGame.setCreator("Miyamoto");
		newGame.setDescription(" The player needs to dodge multiple obstacles to beat the level.");
		newGame.setPrice(20f);
		newGame.setIsbn("0023");
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate=  dateFormat.parse("01/03/1990");
		newGame.setPublished(publishDate);
		
		String imagePath="D:\\studies\\coding\\WEB DEVELOPMENT\\PROJECTS\\bookstoreu\\zadditionals\\images\\donkeykong.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newGame.setImage(imageBytes);
		
		Game createdGame = gameDao.create(newGame);
		
		assertTrue(createdGame.getGameId() > 0);
		
		
	}
	
	@Test
	public void testUpdateGame() throws ParseException, IOException {
		Game existGame = new Game();
		existGame.setGameId(0);
		
		Category category=new Category("Arcade");
		category.setCategoryId(1);
		existGame.setCategory(category);
		
		existGame.setTitle("Donkey Kong revisted");
		existGame.setCreator("Miyamoto");
		existGame.setDescription(" The player needs to dodge multiple obstacles to beat the level.");
		existGame.setPrice(20f);
		existGame.setIsbn("0023");
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate=  dateFormat.parse("01/03/1990");
		existGame.setPublished(publishDate);
		
		String imagePath="D:\\studies\\coding\\WEB DEVELOPMENT\\PROJECTS\\bookstoreu\\zadditionals\\images\\donkeykong.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existGame.setImage(imageBytes);
		
		Game updatedGame = gameDao.update(existGame);
		
		assertEquals(updatedGame.getTitle(), "Donkey Kong revisted");
		
	}
	
	@Test(expected= EntityNotFoundException.class)
	public void testDeleteGameFail() {
		Integer gameId=100;
		gameDao.delete(gameId);
		
	}
	
	@Test
	public void testDeleteGameSuccess() {
		Integer gameId=2;
		gameDao.delete(gameId);
		assertTrue(true);
	}

	@Test
	public void testGetGameFail() {
		Integer gameId=100;
		Game game = gameDao.get(gameId);
		
		assertNull(game);
	}
	
	@Test
	public void testGetGameSuccess() {
		Integer gameId=1;
		Game game = gameDao.get(gameId);
		
		assertNotNull(game);
	}
	
	@Test
	public void testListAll() {
		List<Game> listGames = gameDao.listAll();
		
		assertFalse(listGames.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title="not exist";
		Game game= gameDao.findByTitle(title);
		
		assertNull(game);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title="Pac man";
		Game game= gameDao.findByTitle(title);
		
		System.out.println(game.getCreator());
		
		assertNotNull(game);
	}
	
	@Test
	public void testCount() {
		long totalGames= gameDao.count();
		
		assertEquals(2, totalGames);
	}
	
	@Test
	public void testListNewGames() {
		List<Game> listNewGames = gameDao.listNewGames();
		
		assertEquals(4, listNewGames.size());
	}
	
	@Test
	public void testSearchGameInTitle() {
		String keyword="Kong";
		List<Game> result = gameDao.search(keyword);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void testSearchGameInCreator() {
		String keyword="Miyamoto";
		List<Game> result = gameDao.search(keyword);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void testSearchGameInDescription() {
		String keyword="player";
		List<Game> result = gameDao.search(keyword);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void testListByCategory() {
		int categoryId=1;
		
		List <Game> listGames=gameDao.listByCategory(categoryId);
		
		assertTrue(listGames.size() > 0);
	}
	
	@Test
	public void testCountByCategory() {
		int categoryId=1;
		long numOfGames = gameDao.countByCategory(categoryId);
		
		assertTrue(numOfGames == 6);
	}
	
}
