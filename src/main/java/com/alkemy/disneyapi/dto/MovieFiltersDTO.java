package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
	private String title;
	private String genre;
	private String order;

	public MovieFiltersDTO(){
	}

	public MovieFiltersDTO(String name, String genre, String order) {
		this.title = title;
		this.genre = genre;
		this.order = order;
	}

}
