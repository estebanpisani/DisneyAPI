package com.alkemy.disneyapi.repositories.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entities.Movie;

@Component
public class MovieSpecification {
	
	public Specification<Movie> getByFilters(MovieFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) ->{
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("title")),
								"%" + filtersDTO.getName().toLowerCase()+"%")
				);
			}	
			
			//TODO Agregar filtro de Genre
			//Remove duplicates
			query.distinct(true);
			
			//Order resolver
			
			String orderByField = "title";
			query.orderBy(
					filtersDTO.orderASC() ?
							criteriaBuilder.asc(root.get(orderByField)) :
								criteriaBuilder.desc(root.get(orderByField))	
					);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
		
	}
}
