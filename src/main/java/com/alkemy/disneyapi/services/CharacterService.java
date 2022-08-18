package com.alkemy.disneyapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.entities.Character;
import com.alkemy.disneyapi.mapper.CharacterMapper;
import com.alkemy.disneyapi.repositories.CharacterRepository;
import com.alkemy.disneyapi.repositories.specifications.CharacterSpecification;

@Service
public class CharacterService {

	@Autowired
	CharacterRepository characterRepo;
	@Autowired
	CharacterMapper characterMapper;
	@Autowired
	CharacterSpecification characterSpecification;


	//CREATE
	/*
	 * Create a character with dto's attributes.
	 * Returns saved DTO.
	 */
	public CharacterDTO saveCharacter(CharacterDTO dto) {
		Character character = characterMapper.characterDTO2Entity(dto);
		characterRepo.save(character);
		CharacterDTO result = characterMapper.characterEntity2DTO(character, false);
		return result;
	}
	
	//UPDATE
	/*
	 * Busca el personaje por su id en la base de datos y reemplaza sus atributos por los del DTO. No llegué a validar que los datos no se cambien si llegan nulos por lo que se necesita que se le pase la información completa.
	 */
	public CharacterDTO updateCharacter(String id, CharacterDTO dto) throws Exception {
		Character character = characterRepo.getById(id);

		character.setName(dto.getName());
		character.setAge(dto.getAge());
		character.setImage(dto.getImage());
		character.setWeight(dto.getWeight());
		character.setStory(dto.getStory());
		characterRepo.save(character);
		return characterMapper.characterEntity2DTO(character, false);

	}
	
	//READ
	
	//Get all characters
	public List<CharacterBasicDTO> getAll(){
		List<CharacterBasicDTO> dtos= characterMapper.characterEntityList2BasicDTOList(characterRepo.findAll());
		return dtos;
	}
	
	//Get single character
	public CharacterDTO getCharacterDetail(String id) {
		CharacterDTO dto = characterMapper.characterEntity2DTO(characterRepo.getById(id), false);	
		return dto;	
	}
	
	//No conseguí hacer funcionar filtros combinados. Por lo que creé filtros por separado.
	//Get by name
	public List<CharacterDTO> getCharactersByName(String name){
		return characterMapper.characterEntityList2DTOList(characterRepo.getCharactersByName(name), false);
	}
	
	//Get by age
	public List<CharacterBasicDTO> getCharactersByAge(Integer age){
		return characterMapper.characterEntityList2BasicDTOList(characterRepo.getCharactersByAge(age));
	}

	//Get all by asc title
	/*
	public List<CharacterBasicDTO> getCharactersAsc(){
		return characterMapper.characterEntityList2BasicDTOList(characterRepo.getAllCharactersByNameAsc());
	}
	
	//Get all by desc title
	public List<CharacterBasicDTO> getCharactersDesc(){
		return characterMapper.characterEntityList2BasicDTOList(characterRepo.getAllCharactersByNameDesc());
	}
	
	*/
	//DELETE
	@Transactional
	public void deleteCharacter(String id) {
		characterRepo.deleteById(id);
	}
	
	
}
