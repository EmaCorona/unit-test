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
    public List<PokemonDto> findAllPokemon() {
        log.info("REQUEST START - About to request all the pokemons");

        List<PokemonEntity> entities = pokemonRepository.findAll();

        if (!CollectionUtils.isEmpty(entities)) {
            List<PokemonDto> pokemonsList = pokemonMapper.mapListToDto(entities);
            log.info("REQUEST END - Pokemon list retrieved");
            return pokemonsList;
        } else {
            throw new PokemonNotFoundException();
        }
    }
}
