package com.alkemy.disneyapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.services.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieServ;
	
	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getAllMovies(){
		return ResponseEntity.ok().body(movieServ.getAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> getMovieDetails(@PathVariable String id){
		return ResponseEntity.ok().body(movieServ.getMovieDetail(id));		
	}

	
	@PostMapping(path = "/save", consumes = "application/json")
	public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) throws Exception{
		MovieDTO savedMovie= movieServ.saveMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieDTO> update(@PathVariable String id, @RequestBody MovieDTO movie) throws Exception{
		MovieDTO result = movieServ.updateMovie(id, movie);
		return ResponseEntity.ok().body(result);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){	
		movieServ.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	
	//FILTERS
	//No logré hacer funcionar filtros combinados, por lo que creé filtros por separado usando los path indicados en el challenge.
	
	@GetMapping("?name={nombre}")
	public ResponseEntity<List<MovieDTO>> getMoviesByTitle(@PathVariable String nombre){
		return ResponseEntity.ok().body(movieServ.getMoviesByTitle(nombre));	
	}
	
	@GetMapping("?genre={genreId}")
	public ResponseEntity<List<MovieBasicDTO>> getMoviesByGenre(@PathVariable String genreId){
		return ResponseEntity.ok().body(movieServ.getMoviesByGenreId(genreId));	
	}	
	
	@GetMapping("?order={order}")
	public ResponseEntity<List<MovieBasicDTO>> getMoviesByAsc(@PathVariable String order){
		if(order.equalsIgnoreCase("desc")) {
			return ResponseEntity.ok().body(movieServ.getMoviesDesc());
		}
		return ResponseEntity.ok().body(movieServ.getMoviesAsc());	
	}
}