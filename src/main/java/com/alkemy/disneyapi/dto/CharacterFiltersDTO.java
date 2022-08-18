package com.alkemy.disneyapi.dto;

import java.util.List;
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
	private Double weight;
	private List<String> movies;
	private String order;
	
	public CharacterFiltersDTO(String name, Integer age, Double weight, List<String> movies, String order) {

		this.name = name;
		this.age = age;
		this.weight = weight;
		this.movies = movies;
		this.order = order;
	}

	public CharacterFiltersDTO() {

	}
}
