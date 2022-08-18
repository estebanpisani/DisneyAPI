package com.alkemy.disneyapi.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entities.Character;
import com.alkemy.disneyapi.entities.Movie;
import com.alkemy.disneyapi.repositories.MovieRepository;
import com.alkemy.disneyapi.services.MovieService;

@Component
public class CharacterMapper {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	MovieService movieServ;
	@Autowired
	MovieMapper movieMapper;
	
	
	/*
	 * Crea una entidad con los atributos del DTO que llega por parámetro.
	 * Retorna la entidad creada.
	 */
	
	public Character characterDTO2Entity(CharacterDTO dto) {
		Character character = new Character();
		character.setName(dto.getName());
		character.setAge(dto.getAge());
		character.setImage(dto.getImage());
		character.setStory(dto.getStory());
		character.setWeight(dto.getWeight());
			if(dto.getMovies() != null && !dto.getMovies().isEmpty()) {
				for (MovieBasicDTO movieDTO : dto.getMovies()) {
					Optional<Movie> result = movieRepository.findById(movieDTO.getId());

					if (result.isPresent()) {
						Movie movie = result.get();
						character.getMovies().add(movie);
						movie.getCharacters().add(character);
						movieRepository.save(movie);
					}
				}
			}
		return character;
	}
	/*
	 * Crea un objeto DTO con los atributos de la entidad solicitada.
	 * @param loadMovies(boolean): crea una lista con información básica de las películas del personaje (MovieBasicDTO) y se la agrega al DTO. 
	 * @return Retorna el objeto DTO creado.
	 */
	public CharacterDTO characterEntity2DTO(Character character, boolean loadMovies) {
		CharacterDTO dto = new CharacterDTO();
		dto.setId(character.getId());
		dto.setName(character.getName());
		dto.setAge(character.getAge());
		dto.setImage(character.getImage());
		dto.setStory(character.getStory());
		dto.setWeight(character.getWeight());
		if (loadMovies) {
			List<MovieBasicDTO> moviesBasicDTO = this.movieMapper.movieEntityList2BasicDTOList(character.getMovies());
			dto.setMovies(moviesBasicDTO);
		}
		return dto;
	}
	
	public CharacterDTO characterEntity2BasicDTO(Character character) {
		CharacterDTO dto = new CharacterDTO();
		dto.setName(character.getName());
		dto.setImage(character.getImage());
		return dto;
	}
	
	public List<CharacterDTO> characterEntityList2DTOList(List<Character> characters, boolean loadMovies){
		List<CharacterDTO> dtos = new ArrayList<>();
		for (Character character : characters) {
			dtos.add(this.characterEntity2DTO(character, loadMovies));
		}
		return dtos;
	}
	
	public List<Character> DTOList2characterEntityList(List<CharacterDTO> dtos){
		List<Character> characters = new ArrayList<>();
		for (CharacterDTO dto : dtos) {
			characters.add(this.characterDTO2Entity(dto));
		}
		return characters;
	}
	
	public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<Character> characters){
		List<CharacterBasicDTO> dtos= new ArrayList<>();
		for (Character character : characters) {
			CharacterBasicDTO dto = new CharacterBasicDTO();
			dto.setName(character.getName());
			dto.setImage(character.getImage());
			dtos.add(dto);
		}
		return dtos;
	}
	
	
}
