package com.alkemy.disneyapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieServ;
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> getMovieDetails(@PathVariable String id) throws Exception {
		return ResponseEntity.ok().body(movieServ.getMovieDetails(id));
	}

	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getByFilters(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String genre,
			@RequestParam(required = false, defaultValue = "ASC") String order
	){
		List<MovieBasicDTO> movies = movieServ.findByFilters(name, genre, order);
		return ResponseEntity.ok(movies);
	}
	
	@PostMapping
	public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movie) throws Exception{
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
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}