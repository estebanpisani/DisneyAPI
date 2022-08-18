package com.alkemy.disneyapi.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	private String id;
	private String name;
	private String image;
	private Integer age;
	private Double weight;
	private String story;
	private List<MovieBasicDTO> movies;
}
