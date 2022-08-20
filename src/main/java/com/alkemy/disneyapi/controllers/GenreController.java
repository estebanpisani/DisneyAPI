package com.alkemy.disneyapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.mapper.GenreMapper;
import com.alkemy.disneyapi.services.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {
	@Autowired
	private GenreService genreServ;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> getAllGenres() throws Exception {
		return ResponseEntity.ok().body(genreServ.getAllGenres());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getGenreDetails(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body(genreServ.getGenreDetails(id));
	}

	
	@PostMapping
	public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO dto) throws Exception{
		GenreDTO savedGenre = genreServ.saveGenre(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> updateGenre(@PathVariable String id, @RequestBody GenreDTO dto) throws Exception{
		GenreDTO result = genreServ.updateGenre(id, dto);
		return ResponseEntity.ok().body(result);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGenreById(@PathVariable String id) throws Exception{
		genreServ.deleteGenreById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
