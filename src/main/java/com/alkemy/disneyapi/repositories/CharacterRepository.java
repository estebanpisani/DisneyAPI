package com.alkemy.disneyapi.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.Character;
import com.alkemy.disneyapi.entities.Movie;
@Repository
public interface CharacterRepository extends JpaRepository<Character, String>{
	
	List<Character> findAll(Specification<Character> spec);
	
	@Query("SELECT a FROM Character a WHERE a.name = :name")
	public List<Character> getCharactersByName(@Param("name") String name);
	
	@Query("SELECT a FROM Character a WHERE a.age = :age")
	public List<Character> getCharactersByAge(@Param("age") Integer age);
	
	@Query("SELECT a FROM Character a WHERE a.weight = :weight")
	public List<Character> getCharactersByWeight(@Param("weight") Double weight);	
	
	@Query("SELECT a FROM Character a WHERE a.movies.id = :id")
	public List<Character> getCharactersByMovies(@Param("id") String id);
	/*
	@Query("SELECT a FROM Character a ORDER BY a.name ASC")
	public List<Character> getAllCharactersByNameAsc();
	
	@Query("SELECT a FROM Character a ORDER BY a.name DESC")
	public List<Character> getAllCharactersByNameDesc();
	*/
}
	
	