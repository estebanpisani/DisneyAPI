package com.alkemy.disneyapi.dto;


import java.util.List;

import com.alkemy.disneyapi.entities.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
	private String id;
	private String name;
	private String image;
//	private List<Movie> movies;
}
