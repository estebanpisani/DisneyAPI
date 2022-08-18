package com.alkemy.disneyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {	
	List<Genre> findByName(String name);
}
