package it.corona.unitest.service;

import it.corona.unitest.model.dto.CreatePokemonRequestDTO;
import it.corona.unitest.model.dto.DeletePokemonRequestDTO;
import it.corona.unitest.model.dto.ResponseDTO;
import it.corona.unitest.model.dto.UpdatePokemonRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PokemonService {
    @Transactional
    ResponseDTO createPokemon(CreatePokemonRequestDTO requestDTO);
    ResponseDTO findPokemonById(Long pokemonId);
    @Transactional
    ResponseDTO updatePokemon(UpdatePokemonRequestDTO requestDTO);
    ResponseDTO deletePokemon(DeletePokemonRequestDTO requestDTO);
}