package it.corona.unitest.service;

import it.corona.unitest.model.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PokemonService {
    @Transactional
    ResponseDTO createPokemon(CreatePokemonRequestDTO requestDTO);
    ResponseDTO findPokemonById(Long pokemonId);
    @Transactional
    ResponseDTO updatePokemon(UpdatePokemonRequestDTO requestDTO);
    ResponseDTO deletePokemon(DeletePokemonRequestDTO requestDTO);
}