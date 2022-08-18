package com.alkemy.disneyapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.entities.Genre;
import com.alkemy.disneyapi.mapper.GenreMapper;
import com.alkemy.disneyapi.repositories.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	GenreRepository genreRepo;
	@Autowired
	GenreMapper genreMapper;	
	
	@Transactional
	//CREATE
	public GenreDTO saveGenre(GenreDTO dto) {
		Genre genre = genreMapper.genreDTO2Entity(dto);
		genreRepo.save(genre);
		GenreDTO result = genreMapper.genreEntity2DTO(genre, false);
		return result;
	}
	
	@Transactional
	//UPDATE
	public GenreDTO updateGenre(String id, GenreDTO dto) throws Exception {
		Genre genre = genreRepo.getById(id);
		genre = genreMapper.genreDTO2Entity(dto);
		genreRepo.save(genre);
		GenreDTO result = genreMapper.genreEntity2DTO(genre, false);
		return result;
	}
	
	//READ
	
	//Get all genres
	public List<GenreDTO> getAll(){
		List<GenreDTO> dtos= genreMapper.genreEntityList2DTOList(genreRepo.findAll(), false);
		return dtos;
	}
	
	//Get single genre
	public GenreDTO getGenreDetail(String id) {
		Genre genre = genreRepo.getById(id);
		GenreDTO dto = genreMapper.genreEntity2DTO(genre, true);	
		return dto;	
	}
	
	//Get genre by name
	public Genre getGenreByName(String name) {
		Genre genre = new Genre();
		if(genreRepo.findByName(name).size()==1) {
			genre = genreRepo.findByName(name).get(0);
		}else {
			genre=null;
		}
		return genre;
	}

	@Transactional
	//DELETE
	public void deleteGenreById(String id) {
		genreRepo.deleteById(id);
	}
}
