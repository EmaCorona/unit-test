package it.corona.unitest.service;

import it.corona.unitest.model.dto.DeletePokemonRequestDTO;
import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.dto.ResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PokemonService {
    @Transactional
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonDto findById(Long pokemonId);
    List<PokemonDto> findAllPokemon();
    @Transactional
    PokemonDto updatePokemon(PokemonDto pokemonDto, Long pokemonId);
    ResponseDTO deletePokemonById(DeletePokemonRequestDTO requestDTO);
}