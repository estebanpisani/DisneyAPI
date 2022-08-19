package com.alkemy.disneyapi.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entities.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entities.Movie;
import com.alkemy.disneyapi.mapper.MovieMapper;
import com.alkemy.disneyapi.repositories.GenreRepository;
import com.alkemy.disneyapi.repositories.MovieRepository;
import com.alkemy.disneyapi.repositories.specifications.MovieSpecification;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	MovieMapper movieMapper;	
	@Autowired
	MovieSpecification movieSpecification;
	@Autowired
	GenreService genreServ;
	@Transactional
	//CREATE
	public MovieDTO saveMovie(MovieDTO dto) {
		Movie movie = movieMapper.movieDTO2Entity(dto);
		Movie newMovie = movieRepo.save(movie);
		System.out.println(newMovie.getGenreId());
		MovieDTO result = movieMapper.movieEntity2DTO(newMovie, false);
		return result;
	}
	
	@Transactional
	//UPDATE
	public MovieDTO updateMovie(String id, MovieDTO dto) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		try {
			Optional<Movie> result = movieRepo.findById(id);
			if (result.isPresent()) {
				Movie movie = result.get();
				if(dto.getTitle() != null){
				movie.setTitle(dto.getTitle());
				}
				if(dto.getRate() != null) {
					movie.setRate(dto.getRate());
				}
				if(dto.getCreationDate() != null){
					movie.setCreationDate(LocalDate.parse(dto.getCreationDate(), formatter));
				}
				if(dto.getImage() != null){
					movie.setImage(dto.getImage());
				}
				if(dto.getGenreId() != null){
					movie.setGenreId(dto.getGenreId());
				}
				Movie newMovie = movieRepo.save(movie);
				return movieMapper.movieEntity2DTO(newMovie, false);
			}else{
				throw new Exception("Movie not found.");
			}
		} catch (Exception e){
			throw new Exception("Movie not found.");
		}
	}
	
	//READ

	public List<MovieBasicDTO> getAllMovies(){
		List<MovieBasicDTO> dtos= movieMapper.movieEntityList2BasicDTOList(movieRepo.findAll());
		return dtos;
	}

	public MovieDTO getMovieDetails(String id) throws Exception {
		try{
			Optional<Movie> result = movieRepo.findById(id);
			if(result.isPresent()){
				Movie movie = result.get();
				MovieDTO dto = movieMapper.movieEntity2DTO(movie, true);
				return dto;
			}else{
				throw new Exception("Movie not found.");
			}
		} catch (Exception e) {
			throw new Exception("Movie not found.");
		}
	}

	public List<MovieDTO> findByFilters(String name, String genre, String order) {
		MovieFiltersDTO filtersDTO = new MovieFiltersDTO();

		if(name != null && !name.isEmpty()){
			filtersDTO.setTitle(name);
		}
		if(genre != null && !genre.isEmpty()){
			filtersDTO.setGenre(genre);
		}
		filtersDTO.setOrder(order);

		List<Movie> movies = movieRepo.findAll(movieSpecification.getByFilters(filtersDTO));
		List<MovieDTO> movieDTOS = movieMapper.movieEntityList2DTOList(movies, false);
		return movieDTOS;
	}

	// DELETE
	public void deleteById(String id) {	
		movieRepo.deleteById(id);
	}
}
