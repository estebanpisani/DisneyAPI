package com.alkemy.disneyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {
	List<Movie> findAll(Specification<Movie> spec);
}
