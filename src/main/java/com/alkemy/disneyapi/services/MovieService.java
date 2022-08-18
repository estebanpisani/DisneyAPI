package com.alkemy.disneyapi.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
	GenreRepository genreRepo;
	@Autowired
	MovieSpecification movieSpecification;
	@Autowired
	GenreService genreServ;
	@Transactional
	//CREATE
	public MovieDTO saveMovie(MovieDTO dto) {
		Movie movie = new Movie();
		movie = movieMapper.movieDTO2Entity(dto);
		movieRepo.save(movie);
		MovieDTO result = movieMapper.movieEntity2DTO(movie, false);
		return result;
	}
	
	@Transactional
	//UPDATE
	public MovieDTO updateMovie(String id, MovieDTO dto) throws Exception {
		//Película existente.
		Movie movie = movieRepo.getById(id);
		//Seteo de Película existente con datos nuevos.
			movie.setTitle(dto.getTitle());
			movie.setRate(dto.getRate());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			movie.setCreationDate(LocalDate.parse(dto.getCreationDate(), formatter ));
			movie.setImage(dto.getImage());
			movie.setGenre(genreServ.getGenreByName(dto.getGenre()));
			
		movieRepo.save(movie);
		return movieMapper.movieEntity2DTO(movie, false);
	}
	
	//READ
	
	//Get all movies
	public List<MovieBasicDTO> getAll(){
		List<MovieBasicDTO> dtos= movieMapper.movieEntityList2BasicDTOList(movieRepo.findAll());
		return dtos;
	}
	
	//Get single movie
	public MovieDTO getMovieDetail(String id) {
		Movie movie = movieRepo.getById(id);
		MovieDTO dto = movieMapper.movieEntity2DTO(movie, true);	
		return dto;	
	}
	
	public MovieDTO findById(String id) {
		Movie movie = new Movie();
		Optional<Movie> opt = movieRepo.findById(id);
		if(opt.isPresent()) {
			movie = opt.get();
		}
		return movieMapper.movieEntity2DTO(movie, false);
	}
	public MovieDTO findByTitle(String title) {
		Movie movie = new Movie();
		List<Movie> list = movieRepo.findByTitle(title);
		if(!list.isEmpty()||list.get(0)!=null) {
			movie = list.get(0);
		}
		return movieMapper.movieEntity2DTO(movie, false);
	}

	//No conseguí hacer funcionar filtros combinados. Por lo que creé filtros por separado.
	//Get by title
	public List<MovieDTO> getMoviesByTitle(String title){
		return movieMapper.movieEntityList2DTOList(movieRepo.getMoviesByTitle(title), false);
	}
	
	//Get by genre
	public List<MovieBasicDTO> getMoviesByGenreId(String genreId){
		return movieMapper.movieEntityList2BasicDTOList(movieRepo.getMoviesByGenreId(genreId));
	}

	//Get all by asc title
	public List<MovieBasicDTO> getMoviesAsc(){
		return movieMapper.movieEntityList2BasicDTOList(movieRepo.getAllMoviesByTitleAsc());
	}
	
	//Get all by desc title
	public List<MovieBasicDTO> getMoviesDesc(){
		return movieMapper.movieEntityList2BasicDTOList(movieRepo.getAllMoviesByTitleDesc());
	}
	@Transactional
	//HARD DELETE
	public void deleteById(String id) {	
		movieRepo.deleteById(id);
	}
}
