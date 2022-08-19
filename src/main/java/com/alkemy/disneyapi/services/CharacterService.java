package com.alkemy.disneyapi.services;

import java.util.List;
import java.util.Optional;

import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
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
	public CharacterDTO saveCharacter(CharacterDTO dto) {
		Character character = characterMapper.characterDTO2Entity(dto);
		characterRepo.save(character);
		CharacterDTO result = characterMapper.characterEntity2DTO(character, false);
		return result;
	}
	
	//UPDATE
	public CharacterDTO updateCharacter(String id, CharacterDTO dto) throws Exception {

		try{
			Optional<Character> result = characterRepo.findById(id);

			if(result.isPresent()){
				Character character = result.get();

				if(dto.getName() != null & !dto.getName().isEmpty()){
					character.setName(dto.getName());
				}
				if(dto.getAge() != null){
					character.setAge(dto.getAge());
				}
				if(dto.getWeight() != null){
					character.setWeight(dto.getWeight());
				}
				//TODO set Movies
				characterRepo.save(character);

				CharacterDTO dtoUpdated = characterMapper.characterEntity2DTO(character, false);
				return dtoUpdated;
			}else{
				throw new Exception("Character not found.");
			}
		} catch(Exception e){
			throw new Exception("Character not found.");
		}
	}
	
	//READ
	public CharacterDTO getCharacterDetails(String id) throws Exception {
		try{
			Optional<Character> result = characterRepo.findById(id);
			if(result.isPresent()){
				Character character = result.get();
				CharacterDTO dto = characterMapper.characterEntity2DTO(character, true);
				return dto;
			}else{
				throw new Exception("Character not found.");
			}
		} catch (Exception e) {
			throw new Exception("Character not found.");
		}
	}

	public List<CharacterBasicDTO> getByFilters(String name, Integer age, Double weight, List<String> movies, String order){
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO();
		if(name != null && !name.isEmpty()) {
			filtersDTO.setName(name);
		}
		if(age != null){
			filtersDTO.setAge(age);
		}
		if(weight != null){
			filtersDTO.setWeight(weight);
		}
		if(movies != null){
			filtersDTO.setMovies(movies);
		}
		filtersDTO.setOrder(order);

		List<Character> characters= characterRepo.findAll(characterSpecification.getByFilters(filtersDTO));
		List<CharacterBasicDTO> dtos = characterMapper.characterEntityList2BasicDTOList(characters);
		return dtos;
	}

	//DELETE
	public void deleteCharacter(String id) {
		characterRepo.deleteById(id);
	}
	
	
}
