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

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.mapper.GenreMapper;
import com.alkemy.disneyapi.services.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {
	
	@Autowired
	private GenreService genreServ;
	@Autowired
	GenreMapper genreMapper;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> getAllGenres(){
		return ResponseEntity.ok().body(genreServ.getAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getGenreDetails(@PathVariable String id){
		return ResponseEntity.ok().body(genreServ.getGenreDetail(id));		
	}

	
	@PostMapping(path = "/save", consumes = "application/json")
	public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto) throws Exception{
		GenreDTO savedGenre = genreServ.saveGenre(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> update(@PathVariable String id, @RequestBody GenreDTO dto) throws Exception{
		GenreDTO result = genreServ.updateGenre(id, dto);
		return ResponseEntity.ok().body(result);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) throws Exception{
		genreServ.deleteGenreById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}

}
