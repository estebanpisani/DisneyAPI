package com.alkemy.disneyapi.repositories.specifications;

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
			
			//Filtro name
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("name")),
								"%" + filtersDTO.getName().toLowerCase()+"%")
				);
			}	
					
			//Filtro age
			if(StringUtils.hasLength(filtersDTO.getAge().toString())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("age")),
								"%" + filtersDTO.getName().toLowerCase()+"%")
				);
			}	
			

			//Filtro Movies
			/*
			if(!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
				Join<Movie, Character> join = root.join("movies", JoinType.INNER);
				Expression<String> moviesId = join.get("id");
				predicates.add(moviesId.in(filtersDTO.getMovies()));
			}
			*/
			//Remove duplicates
			query.distinct(true);
			
			//Order resolver
			
			String orderByField = "name";
			query.orderBy(
					filtersDTO.orderASC() ?
							criteriaBuilder.asc(root.get(orderByField)) :
								criteriaBuilder.desc(root.get(orderByField))	
					);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
		
	}
}
