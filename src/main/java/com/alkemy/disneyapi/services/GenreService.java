package com.alkemy.disneyapi.services;

import java.util.List;
import java.util.Optional;

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
		GenreDTO result = genreMapper.genreEntity2DTO(genre);
		return result;
	}
	
	@Transactional
	//UPDATE
	public GenreDTO updateGenre(String id, GenreDTO dto) throws Exception {
		try {
			Optional<Genre> result = genreRepo.findById(id);
			if (result.isPresent()) {
				Genre genre = result.get();
				genre.setName(dto.getName());
				genre.setImage(dto.getImage());
				genreRepo.save(genre);
				GenreDTO genreDTO = genreMapper.genreEntity2DTO(genre);
				return genreDTO;
			} else {
				throw new Exception("Genre not found.");
			}
		} catch (Exception e) {
			throw new Exception("Genre not found.");
		}
	}
	
	//READ
	
	//Get all genres
	public List<GenreDTO> getAllGenres() throws Exception {
		try {
			List<GenreDTO> dtos = genreMapper.genreEntityList2DTOList(genreRepo.findAll());
			return dtos;
		} catch (Exception e) {
			throw new Exception("Genres not found");
		}
	}
	
	//Get single genre
	public GenreDTO getGenreDetails(String id) throws Exception {
		try {
			Optional<Genre> result = genreRepo.findById(id);
			if (result.isPresent()) {
				Genre genre = result.get();
				GenreDTO dto = genreMapper.genreEntity2DTO(genre);
				return dto;
			} else {

				throw new Exception("Genre not found.");
			}
		} catch (Exception e) {
			throw new Exception("Genre not found.");
		}
	}

	@Transactional
	//DELETE
	public void deleteGenreById(String id) {
		genreRepo.deleteById(id);
	}
}
