package com.alkemy.disneyapi.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
	private String id;
	private String title;
	private Integer rate;
	private String creationDate;
	private String image;
	private List<CharacterBasicDTO> characters;
	private String genreId;
	private GenreDTO genre;
}
