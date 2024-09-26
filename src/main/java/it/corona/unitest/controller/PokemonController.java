package it.corona.unitest.controller;

import it.corona.unitest.model.dto.CreatePokemonRequestDTO;
import it.corona.unitest.model.dto.DeletePokemonRequestDTO;
import it.corona.unitest.model.dto.ResponseDTO;
import it.corona.unitest.model.dto.UpdatePokemonRequestDTO;
import it.corona.unitest.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class PokemonController implements PokemonApi {

    private final PokemonService pokemonService;

    private static final String REQUEST_START = "REQUEST START - ";

    private static final String REQUEST_END = "REQUEST END - ";

    @Override
    public ResponseEntity<ResponseDTO> createPokemon(CreatePokemonRequestDTO requestDTO) {
        log.info(REQUEST_START + "CreatePokemon: {}", requestDTO.getPokemonName());
        ResponseDTO responseDTO = pokemonService.createPokemon(requestDTO);
        log.info(REQUEST_END + "CreatePokemon: {}", requestDTO.getPokemonName());
        return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> updatePokemon(UpdatePokemonRequestDTO requestDTO) {
        log.info(REQUEST_START + "UpdatePokemon: {}", requestDTO.getPokemonName());
        ResponseDTO responseDTO = pokemonService.updatePokemon(requestDTO);
        log.info(REQUEST_END + "UpdatePokemon: {}", requestDTO.getPokemonName());
        return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> findPokemonById(Long pokemonId) {
        log.info(REQUEST_START + "FindPokemonById: {}", pokemonId);
        ResponseDTO responseDTO = pokemonService.findPokemonById(pokemonId);
        log.info(REQUEST_END + "FindPokemonById: {}", pokemonId);
        return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> deletePokemon(DeletePokemonRequestDTO requestDTO) {
        log.info(REQUEST_START + "DeletePokemon: {}", requestDTO.getPokemonName());
        ResponseDTO responseDTO = pokemonService.deletePokemonById(requestDTO);
        log.info(REQUEST_END + "DeletePokemon: {}", requestDTO.getPokemonName());
        return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
    }
}