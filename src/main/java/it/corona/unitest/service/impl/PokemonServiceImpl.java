package it.corona.unitest.service.impl;

import it.corona.unitest.exception.PokemonNotFoundException;
import it.corona.unitest.model.dto.*;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.repository.PokemonRepository;
import it.corona.unitest.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    private final PokemonMapper pokemonMapper;

    @Override
    public ResponseDTO createPokemon(CreatePokemonRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            if (!pokemonRepository.existsByName(requestDTO.getPokemonName())) {
                log.info("About to create the requested pokemon");

                PokemonEntity pokemonToCreate = PokemonEntity.builder()
                        .pokedexId(requestDTO.getPokedexId())
                        .name(requestDTO.getPokemonName())
                        .type(requestDTO.getPokemonType())
                        .build();

                log.info("Saving the Pokemon");
                PokemonEntity savedPokemon = pokemonRepository.save(pokemonToCreate);

                log.info("Mapping entity to dto");
                PokemonDTO pokemonDTO = pokemonMapper.mapToDto(savedPokemon);

                log.info("Pokemon successfully created");

                responseDTO.setPokemonDTO(pokemonDTO);
                responseDTO.setHttpStatusCode(HttpStatus.CREATED.value());

            } else {
                String error = "Pokemon already exists";
                log.info(error);
                responseDTO.setError(error);
                responseDTO.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
                return responseDTO;
            }

        } catch (Exception e) {
            String error = "An unexpected error occured during the creation of the pokemon";
            log.info("{}: {}", error, requestDTO.getPokemonName());
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return responseDTO;
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO findPokemonById(Long pokemonId) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            log.info("About to request the desired pokemon");
            PokemonEntity pokemonEntity = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);

            log.info("Pokemon successfully requested, mapping Entity to Dto");
            PokemonDTO pokemonDTO = pokemonMapper.mapToDto(pokemonEntity);
            responseDTO.setPokemonDTO(pokemonDTO);
            responseDTO.setHttpStatusCode(HttpStatus.OK.value());

        } catch (PokemonNotFoundException e) {
            String error = "Pokemon with id: " + pokemonId + " was not found";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            log.info(error);
            return responseDTO;

        } catch (Exception e) {
            String error = "An unexpected error occurred during the request of the pokemon with id: " + pokemonId;
            log.info("{}: {}", error, pokemonId);
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return responseDTO;
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO updatePokemon(UpdatePokemonRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            log.info("About to update the requested Pokemon: {}", requestDTO.getPokemonName());
            PokemonEntity pokemonToUpdate = pokemonRepository.findById(requestDTO.getPokemonId()).orElseThrow(PokemonNotFoundException::new);
            pokemonToUpdate.setPokedexId(requestDTO.getPokedexId());
            pokemonToUpdate.setName(requestDTO.getPokemonName());
            pokemonToUpdate.setType(requestDTO.getPokemonType());

            log.info("Updating the pokemon");
            PokemonEntity savedPokemon = pokemonRepository.save(pokemonToUpdate);

            log.info("Mapping entity to dto");
            PokemonDTO pokemonDTO = pokemonMapper.mapToDto(savedPokemon);

            responseDTO.setPokemonDTO(pokemonDTO);
            responseDTO.setHttpStatusCode(HttpStatus.OK.value());

        } catch (PokemonNotFoundException e) {
            String error = "Pokemon to update was not found";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            log.info(error);
            return responseDTO;

        } catch (Exception e) {
            String error = "An unexpected error occurred during the update of the pokemon";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.info("{}: {}", error, requestDTO.getPokemonName());
            return responseDTO;
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO deletePokemon(DeletePokemonRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            log.info("About to delete the requested Pokemon");

            log.info("Finding the pokemon to delete");
            PokemonEntity pokemonToDelete = pokemonRepository.findById(requestDTO.getPokemonId()).orElseThrow(PokemonNotFoundException::new);

            log.info("Delingte the pokemon");
            pokemonRepository.delete(pokemonToDelete);

            log.info("Pokemon deleted");

            responseDTO.setPokemonDTO(pokemonMapper.mapToDto(pokemonToDelete));
            responseDTO.setHttpStatusCode(HttpStatus.OK.value());

        } catch (PokemonNotFoundException e) {
            String error = "Pokemon to delete was not found";
            log.info(error);
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            return responseDTO;

        } catch (Exception e) {
            String error = "An unexpected error occurred during the deletion of the pokemon";
            log.info("{}: {}", error, requestDTO.getPokemonName());
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return responseDTO;
        }

        return responseDTO;
    }
}