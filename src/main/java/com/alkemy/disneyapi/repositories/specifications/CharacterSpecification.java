package com.alkemy.disneyapi.repositories.specifications;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
import com.alkemy.disneyapi.entities.Character;
import com.alkemy.disneyapi.entities.Movie;

@Component
public class CharacterSpecification {
	
	public Specification<Character> getByFilters(CharacterFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();

			if(filtersDTO.getName() != null && filtersDTO.getName().isEmpty()) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("name")),
								"%" + filtersDTO.getName().toLowerCase()+"%")
				);
			}	

			if(filtersDTO.getAge() != null && !filtersDTO.getAge().toString().isEmpty()) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("age")),
								"%" + filtersDTO.getAge().toString().toLowerCase()+"%")
				);
			}

			if(filtersDTO.getWeight() != null && !filtersDTO.getWeight().toString().isEmpty()){
				predicates.add(criteriaBuilder.like(root.get("weight"), filtersDTO.getWeight().toString()));
			}

			if(filtersDTO.getMovies() != null && filtersDTO.getMovies().size()>=1 && !filtersDTO.getMovies().isEmpty()){
				Join<Movie, Character> join = root.join("movies", JoinType.INNER);
				Expression<String> movies = join.get("id");
				predicates.add(movies.in(filtersDTO.getMovies()));
			}

			//Remove duplicates
			query.distinct(true);

			//TODO orderByDate
			if(filtersDTO.getOrder().equalsIgnoreCase("desc")){
				query.orderBy(criteriaBuilder.desc(root.get("name")));
			}
			else{
				query.orderBy(criteriaBuilder.asc(root.get("name")));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
}
