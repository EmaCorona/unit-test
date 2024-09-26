package it.corona.unitest.controller;

import it.corona.unitest.model.dto.*;
import it.corona.unitest.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class PokemonController implements PokemonApi {

    private final PokemonService pokemonService;

    private static final String REQUEST_START = "REQUEST START - ";

    private static final String REQUEST_END = "REQUEST END - ";

    @Override
    public ResponseEntity<Void> createPokemon(CreatePokemonRequestDTO requestDTO) {
        log.info("REQUEST START - CreatePokemon: {}", requestDTO.getPokemonName());
        log.info("REQUEST END - CreatePokemon: {}", requestDTO.getPokemonName());
        return null;
    }

    @GetMapping("/find-by-id/{pokemonId}")
    public ResponseEntity<PokemonDto> findById(@PathVariable Long pokemonId) {
        PokemonDto pokemonDto = pokemonService.findById(pokemonId);
        return new ResponseEntity<>(pokemonDto, HttpStatus.OK);
    }

    @GetMapping("/find-all-pokemons")
    public ResponseEntity<List<PokemonDto>> findAllPokemon() {
        List<PokemonDto> pokemons = pokemonService.findAllPokemon();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updatePokemon(UpdatePokemonRequestDTO requestDTO) {
        log.info("REQUEST START - UpdatePokemon: {}", requestDTO.getPokemonName());
        log.info("REQUEST END - UpdatePokemon: {}", requestDTO.getPokemonName());
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deletePokemon(DeletePokemonRequestDTO requestDTO) {
        log.info(REQUEST_START + "DeletePokemon: {}", requestDTO.getPokemonName());
        ResponseDTO responseDTO = pokemonService.deletePokemonById(requestDTO);
        log.info(REQUEST_END + "DeletePokemon: {}", requestDTO.getPokemonName());
        return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
    }
}