package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
	private String name;
	private String creationDate;
	private String genre;
	private String order;

	public MovieFiltersDTO(){
	}

	public MovieFiltersDTO(String name, String creationDate, String order) {
		this.name = name;
		this.creationDate = creationDate;
		this.order = order;
	}

}
