package com.alkemy.disneyapi.dto;

import java.util.Set;

import com.alkemy.disneyapi.entities.Genre;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDTO {
	private String name;
	private Integer age;
	private Set<String> movies;
	private String order;
	
	public CharacterFiltersDTO(String name, Integer age, Set<String> movies, String order) {

		this.name = name;
		this.age = age;
		this.movies = movies;
		this.order = order;
	}	

	public boolean orderASC() {return this.order.compareToIgnoreCase("ASC")==0;}
	public boolean orderDESC() {return this.order.compareToIgnoreCase("DESC")==0;}


}
