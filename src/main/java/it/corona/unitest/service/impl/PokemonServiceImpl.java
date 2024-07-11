package it.corona.unitest.service.impl;

import it.corona.unitest.exception.PokemonNotFoundException;
import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.repository.PokemonRepository;
import it.corona.unitest.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        log.info("REQUEST START - About to create the requested pokemon");
        PokemonEntity pokemonEntity = pokemonMapper.mapToEntity(pokemonDto);
        PokemonEntity savedPokemon = pokemonRepository.save(pokemonEntity);
        log.info("REQUEST END - Pokemon created");
        return pokemonMapper.mapToDto(savedPokemon);
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
    public void deletePokemonById(Long pokemonId) {
        log.info("REQUEST START - About to delete the requested Pokemon");
        PokemonEntity pokemonToDelete = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
        pokemonRepository.delete(pokemonToDelete);
        log.info("REQUEST END - Pokemon deleted");
    }
}