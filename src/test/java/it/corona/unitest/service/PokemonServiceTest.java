package it.corona.unitest.service;

import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.repository.PokemonRepository;
import it.corona.unitest.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PokemonServiceTest {
    @InjectMocks
    private PokemonServiceImpl pokemonService;
    @Mock
    private PokemonMapper pokemonMapper;
    @Mock
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonService_CreatePokemon_ReturnsPokemonDto() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity mockedEntity = PokemonEntity.builder().pokemonId(1L).name("Blastoise").type("Water").build();
        PokemonDto mockedDto = PokemonDto.builder().pokemonId(1L).name("Blastoise").type("Water").build();

        when(pokemonMapper.mapToEntity(any(PokemonDto.class))).thenReturn(mockedEntity);
        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(mockedEntity);
        when(pokemonMapper.mapToDto(any(PokemonEntity.class))).thenReturn(mockedDto);

        /* ***************** ACT ***************** */
        PokemonDto savedPokemon = pokemonService.createPokemon(mockedDto);

        /* ***************** ASSERT ***************** */
        verify(pokemonMapper).mapToEntity(mockedDto);
        verify(pokemonRepository).save(mockedEntity);
        verify(pokemonMapper).mapToDto(mockedEntity);

        assertThat(savedPokemon).isNotNull();
        assertThat(savedPokemon.getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemon.getName()).isEqualTo("Blastoise");
        assertThat(savedPokemon.getType()).isEqualTo("Water");
    }

    @Test
    public void PokemonService_FindAll_ReturnsAllPokemons() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmanderEntity = PokemonEntity.builder().pokemonId(1L).name("Charmander").type("Fire").build();
        PokemonEntity squirtleEntity = PokemonEntity.builder().pokemonId(2L).name("Squirtle").type("Water").build();
        PokemonEntity bulbasaurEntity = PokemonEntity.builder().pokemonId(3L).name("Bulbasaur").type("Grass").build();

        List<PokemonEntity> mockedEntities = new ArrayList<>();
        mockedEntities.add(charmanderEntity);
        mockedEntities.addLast(squirtleEntity);
        mockedEntities.add(bulbasaurEntity);

        PokemonDto charmanderDto = PokemonDto.builder().pokemonId(1L).name("Charmander").type("Fire").build();
        PokemonDto squirtleDto = PokemonDto.builder().pokemonId(2L).name("Squirtle").type("Water").build();
        PokemonDto bulbasaurDto = PokemonDto.builder().pokemonId(3L).name("Bulbasaur").type("Grass").build();

        List<PokemonDto> mockedDtos = new ArrayList<>();
        mockedDtos.add(charmanderDto);
        mockedDtos.add(squirtleDto);
        mockedDtos.add(bulbasaurDto);

        when(pokemonRepository.findAll()).thenReturn(mockedEntities);
        when(pokemonMapper.mapListToDto(anyList())).thenReturn(mockedDtos);

        /* ***************** ACT ***************** */
        List<PokemonDto> resultList = pokemonService.findAllPokemon();

        /* ***************** ASSERT ***************** */
        verify(pokemonRepository).findAll();
        verify(pokemonMapper).mapListToDto(mockedEntities);

        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isGreaterThan(0);
        assertThat(resultList.getFirst().getName()).isEqualTo("Charmander");
        assertThat(resultList.getLast().getName()).isEqualTo("Bulbasaur");
    }
}