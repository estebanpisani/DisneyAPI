package com.alkemy.disneyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	
	List<Movie> findAll(Specification<Movie> spec);
	List<Movie> findByTitle(String title);
	@Query("SELECT a FROM Movie a WHERE a.title LIKE:title")
	public List<Movie> getMoviesByTitle(@Param("title") String title);
	@Query("SELECT a FROM Movie a WHERE a.genre LIKE:genreId")
	public List<Movie> getMoviesByGenreId(@Param("genreId") String genreId);
	
	@Query("SELECT a FROM Movie a ORDER BY a.title ASC")
	public List<Movie> getAllMoviesByTitleAsc();
	
	@Query("SELECT a FROM Movie a ORDER BY a.title DESC")
	public List<Movie> getAllMoviesByTitleDesc();
	
	

}
