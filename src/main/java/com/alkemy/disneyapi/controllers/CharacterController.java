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

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.mapper.CharacterMapper;
import com.alkemy.disneyapi.services.CharacterService;

@Controller
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService characterServ;
	@Autowired
	CharacterMapper characterMapper;

	@GetMapping
	public ResponseEntity<List<CharacterBasicDTO>> getAllCharacters(){
		return ResponseEntity.ok().body(characterServ.getAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> getCharacterDetails(@PathVariable String id){
		return ResponseEntity.ok().body(characterServ.getCharacterDetail(id));		
	}

	
	@PostMapping(path = "/save", consumes = "application/json")
	public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO dto) throws Exception{
		CharacterDTO savedCharacter = characterServ.saveCharacter(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CharacterDTO> update(@PathVariable String id, @RequestBody CharacterDTO dto) throws Exception{
		CharacterDTO result = characterServ.updateCharacter(id, dto);
		return ResponseEntity.ok().body(result);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) throws Exception{
		characterServ.deleteCharacter(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}

	//FILTERS
	//No logré hacer funcionar filtros combinados, por lo que creé filtros por separado usando los path indicados en el challenge.
	
	@GetMapping("?name={nombre}")
	public ResponseEntity<List<CharacterDTO>> getCharactersByName(@PathVariable String nombre){
		return ResponseEntity.ok().body(characterServ.getCharactersByName(nombre));	
	}
	
	@GetMapping("?age={age}")
	public ResponseEntity<List<CharacterBasicDTO>> getCharactersByAge(@PathVariable Integer age){
		return ResponseEntity.ok().body(characterServ.getCharactersByAge(age));	
	}	
	/*
	@GetMapping("?order={order}")
	public ResponseEntity<List<CharacterBasicDTO>> getCharactersByOrder(@PathVariable String order){
		if(order.equalsIgnoreCase("desc")) {
			return ResponseEntity.ok().body(characterServ.getCharactersDesc());
		}
		return ResponseEntity.ok().body(characterServ.getCharactersAsc());	
	}
	*/
}