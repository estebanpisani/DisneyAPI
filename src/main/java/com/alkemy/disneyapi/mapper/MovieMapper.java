package com.alkemy.disneyapi.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entities.Movie;
import com.alkemy.disneyapi.services.GenreService;

@Component
public class MovieMapper {
	//@Autowired
	//CharacterMapper characterMapper;
	@Autowired
	GenreMapper genreMapper;
	
	public Movie movieDTO2Entity(MovieDTO dto) {
		Movie movie = new Movie();
		movie.setTitle(dto.getTitle());
		movie.setRate(dto.getRate());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.parse(dto.getCreationDate(), formatter );
		movie.setCreationDate(localDate);
		movie.setImage(dto.getImage());
		movie.setGenreId(dto.getGenreId());
		return movie;
	}
	
	public MovieDTO movieEntity2DTO(Movie movie, boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(movie.getId());
		dto.setTitle(movie.getTitle());
		dto.setRate(movie.getRate());	
		if(movie.getCreationDate()!=null) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		dto.setCreationDate(movie.getCreationDate().format(formatter));}
		
		dto.setImage(movie.getImage());
		dto.setGenre(genreMapper.genreEntity2DTO(movie.getGenre()));
		/*
		if (loadCharacters) {
			List<CharacterBasicDTO> charactersDTO = this.characterMapper.characterEntityList2BasicDTOList(movie.getCharacters());
			dto.setCharacters(charactersDTO);
		}
		 */
		return dto;
	}
	
	public MovieDTO movieEntity2BasicDTO(Movie movie) {
		MovieDTO dto = new MovieDTO();

		dto.setTitle(movie.getTitle());
		if(movie.getCreationDate()!=null) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		dto.setCreationDate(movie.getCreationDate().format(formatter));}
		dto.setImage(movie.getImage());
		return dto;
	}
	
	public List<MovieDTO> movieEntityList2DTOList(List<Movie> movies, boolean loadCharacters){
		List<MovieDTO> dtos = new ArrayList<>();
		for (Movie movie : movies) {
			dtos.add(this.movieEntity2DTO(movie, false));
		}
		return dtos;
	}
	
	public List<Movie> DTOList2movieEntityList(List<MovieDTO> dtos){
		List<Movie> movies = new ArrayList<>();
		for (MovieDTO dto : dtos) {
			movies.add(movieDTO2Entity(dto));
		}
		return movies;
	}
	
	public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<Movie> movies){
		List<MovieBasicDTO> dtos = new ArrayList<>();
		for (Movie movie : movies) {
			MovieBasicDTO dto = new MovieBasicDTO();
			dto.setTitle(movie.getTitle());
			if(movie.getCreationDate()!=null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			dto.setCreationDate(movie.getCreationDate().toString());}		
			dto.setImage(movie.getImage());
			dtos.add(dto);
		}
		return dtos;
	}

	
}
