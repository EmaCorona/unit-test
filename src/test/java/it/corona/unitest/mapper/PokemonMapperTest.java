package it.corona.unitest.mapper;

import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.utils.PokemonMockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PokemonMapperTest {

    private PokemonMapper pokemonMapper;

    @BeforeEach
    void setUp() {
        pokemonMapper = new PokemonMapper(new ModelMapper());
    }

    @Test
    public void PokemonMapper_MapToEntity_ReturnsCorrectEntity() {
        /* ***************** ARRANGE ***************** */
        PokemonDto dto = PokemonMockUtils.getMockedPikachuDto(true);

        /* ***************** ACT ***************** */
        PokemonEntity resultEntity = pokemonMapper.mapToEntity(dto);

        /* ***************** ASSERT ***************** */
        assertThat(resultEntity.getPokemonId()).isEqualTo(dto.getPokemonId());
        assertThat(resultEntity.getPokedexId()).isEqualTo(dto.getPokedexId());
        assertThat(resultEntity.getName()).isEqualTo(dto.getName());
        assertThat(resultEntity.getType()).isEqualTo(dto.getType());
    }

    @Test
    public void PokemonMapper_MapToEntities_ReturnCorrectEntities() {
        /* ***************** ARRANGE ***************** */
        PokemonDto pikachu = PokemonMockUtils.getMockedPikachuDto(true);
        PokemonDto raichu = PokemonMockUtils.getMockedRaichuDto(true);

        List<PokemonDto> pokemonDtos = List.of(pikachu, raichu);

        /* ***************** ACT ***************** */
        List<PokemonEntity> resultEntities = pokemonMapper.mapListToEntities(pokemonDtos);

        assertThat(resultEntities).isNotNull();
        assertThat(resultEntities).isNotEmpty();
        assertThat(resultEntities.size()).isEqualTo(pokemonDtos.size());
        assertThat(resultEntities.getFirst().getPokemonId()).isEqualTo(pokemonDtos.getFirst().getPokemonId());
        assertThat(resultEntities.getFirst().getPokedexId()).isEqualTo(pokemonDtos.getFirst().getPokedexId());
        assertThat(resultEntities.getFirst().getName()).isEqualTo(pokemonDtos.getFirst().getName());
        assertThat(resultEntities.getLast().getPokemonId()).isEqualTo(pokemonDtos.getLast().getPokemonId());
        assertThat(resultEntities.getLast().getPokedexId()).isEqualTo(pokemonDtos.getLast().getPokedexId());
        assertThat(resultEntities.getLast().getName()).isEqualTo(pokemonDtos.getLast().getName());
    }

    @Test
    public void PokemonMapper_MapToDto_ReturnsCorrectDto() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity entity = PokemonMockUtils.getMockedPikachuEntity(true);

        /* ***************** ACT ***************** */
        PokemonDto resultDto = pokemonMapper.mapToDto(entity);

        /* ***************** ASSERT ***************** */
        assertThat(resultDto.getPokemonId()).isEqualTo(entity.getPokemonId());
        assertThat(resultDto.getPokedexId()).isEqualTo(entity.getPokedexId());
        assertThat(resultDto.getName()).isEqualTo(entity.getName());
        assertThat(resultDto.getType()).isEqualTo(entity.getType());
    }

    @Test
    public void PokemonMapper_MapToDtoList_ReturnCorrectDtoList() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pikachu = PokemonMockUtils.getMockedPikachuEntity(true);
        PokemonEntity raichu = PokemonMockUtils.getMockedRaichuEntity(true);

        List<PokemonEntity> pokemonEntities = List.of(pikachu, raichu);

        /* ***************** ACT ***************** */
        List<PokemonDto> resultDtos = pokemonMapper.mapListToDto(pokemonEntities);

        /* ***************** ASSERT ***************** */
        assertThat(resultDtos).isNotNull();
        assertThat(resultDtos).isNotEmpty();
        assertThat(resultDtos.size()).isEqualTo(pokemonEntities.size());
        assertThat(resultDtos.getFirst().getPokemonId()).isEqualTo(pokemonEntities.getFirst().getPokemonId());
        assertThat(resultDtos.getFirst().getPokedexId()).isEqualTo(pokemonEntities.getFirst().getPokedexId());
        assertThat(resultDtos.getFirst().getName()).isEqualTo(pokemonEntities.getFirst().getName());
        assertThat(resultDtos.getLast().getPokemonId()).isEqualTo(pokemonEntities.getLast().getPokemonId());
        assertThat(resultDtos.getLast().getPokedexId()).isEqualTo(pokemonEntities.getLast().getPokedexId());
        assertThat(resultDtos.getLast().getName()).isEqualTo(pokemonEntities.getLast().getName());
    }
}