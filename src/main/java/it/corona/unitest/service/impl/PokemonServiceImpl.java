package it.corona.unitest.service.impl;

import it.corona.unitest.exception.PokemonNotFoundException;
import it.corona.unitest.model.dto.CreatePokemonRequestDTO;
import it.corona.unitest.model.dto.DeletePokemonRequestDTO;
import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.dto.ResponseDTO;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.repository.PokemonRepository;
import it.corona.unitest.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
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

                PokemonEntity savedPokemon = pokemonRepository.save(pokemonToCreate);
                PokemonDTO pokemonDTO = pokemonMapper.mapToDto(savedPokemon);

                log.info("Pokemon successfully created");

                responseDTO.setPokemonDTO(pokemonDTO);
                responseDTO.setHttpStatusCode(HttpStatus.CREATED.value());

            } else {
                String error = "Pokemon already exists";
                responseDTO.setError(error);
                responseDTO.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
                log.info(error);
                return responseDTO;
            }

        } catch (Exception e) {
            String error = "An unexpected error occured during the creation of the pokemon";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.info("{}: {}", error, requestDTO.getPokemonName());
            return responseDTO;
        }

        return responseDTO;
    }

    @Override
    public PokemonDto findById(Long pokemonId) {
        log.info("REQUEST START - About to request the pokemon with the specified pokemonId");
        PokemonEntity pokemonEntity = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
        PokemonDto pokemonDto = pokemonMapper.mapToDto(pokemonEntity);
        log.info("REQUEST END - Pokemon retrieved");
        return pokemonDto;
    }

    @Override
    public List<PokemonDto> findAllPokemon() {
        log.info("REQUEST START - About to request all the pokemons");

        List<PokemonEntity> entities = pokemonRepository.findAll();

        if (!CollectionUtils.isEmpty(entities)) {
            List<PokemonDto> pokemonsList = pokemonMapper.mapListToDto(entities);
            log.info("REQUEST END - Pokemon list retrieved");
            return pokemonsList;
        } else {
            log.error("REQUEST ERROR: No Pokemon was found");
            throw new PokemonNotFoundException();
        }
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, Long pokemonId) {
        log.info("REQUEST START - About to update the requested Pokemon");
        PokemonEntity pokemonEntity = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
        pokemonEntity.setName(pokemonDto.getName());
        pokemonEntity.setType(pokemonDto.getType());
        pokemonRepository.save(pokemonEntity);
        log.info("REQUEST END - Pokemon Updated");
        return pokemonMapper.mapToDto(pokemonEntity);
    }

    @Override
    public ResponseDTO deletePokemonById(DeletePokemonRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            log.info("About to delete the requested Pokemon");

            PokemonEntity pokemonToDelete = pokemonRepository.findById(requestDTO.getPokemonId()).orElseThrow(PokemonNotFoundException::new);
            pokemonRepository.delete(pokemonToDelete);

            responseDTO.setPokemonDTO(pokemonMapper.mapToDto(pokemonToDelete));
            responseDTO.setHttpStatusCode(HttpStatus.OK.value());

            log.info("Pokemon deleted");

        } catch (PokemonNotFoundException e) {
            String error = "Pokemon to delete was not found";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            log.info(error);
            return responseDTO;

        } catch (Exception e) {
            String error = "An unexpected error occurred during the deletion of the pokemon";
            responseDTO.setError(error);
            responseDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.info("{}: {}", error, requestDTO.getPokemonName());
            return responseDTO;
        }

        return responseDTO;
    }
}