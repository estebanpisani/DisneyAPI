package com.alkemy.disneyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alkemy.disneyapi.entities.Character;
@Repository
public interface CharacterRepository extends JpaRepository<Character, String>, JpaSpecificationExecutor<Character> {
	List<Character> findAll(Specification<Character> spec);
}
	
	