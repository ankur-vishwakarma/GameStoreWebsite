package com.gamestore.entity;
// Generated 6 May, 2020 9:30:56 PM by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category", catalog = "gamestoredb")
@NamedQueries({
	@NamedQuery(name ="Category.findAll", query ="SELECT c FROM Category c ORDER BY c.name"),
	@NamedQuery(name ="Category.countAll", query ="SELECT COUNT(*) FROM Category"),
	@NamedQuery(name ="Category.findByName", query="SELECT c FROM Category c WHERE c.name = :name")
})
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String name;
	private Set<Game> games = new HashSet<Game>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Set<Game> games) {
		this.name = name;
		this.games = games;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Game> getGames() {
		return this.games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

}
