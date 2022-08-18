package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
	private String name;
	private String order;
	
	public MovieFiltersDTO(String name, String order) {

		this.name = name;
		this.order = order;
	}	

	public boolean orderASC() {return this.order.compareToIgnoreCase("ASC")==0;}
	public boolean orderDESC() {return this.order.compareToIgnoreCase("DESC")==0;}


}
