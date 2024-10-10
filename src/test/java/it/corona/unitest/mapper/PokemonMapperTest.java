package it.corona.unitest.mapper;

import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.impl.PokemonMapperImpl;
import it.corona.unitest.utils.PokemonMockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PokemonMapperTest {

    @InjectMocks
    private PokemonMapperImpl pokemonMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void GivenValidDTO_MapToEntities_ReturnsMappedEntity() {
        /* ***************** ARRANGE ***************** */
        PokemonDTO dto = PokemonMockUtils.getMockedPikachuDto(false);
        PokemonEntity entity = PokemonMockUtils.getMockedPikachuEntity(false);

        when(modelMapper.map(dto, PokemonEntity.class)).thenReturn(entity);

        /* ***************** ACT ***************** */
        PokemonEntity result = pokemonMapper.mapToEntity(dto);

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertEquals(result.getPokedexId(), dto.getPokedexId());
        assertEquals(result.getName(), dto.getName());
        assertEquals(result.getType(), dto.getType());

        verify(modelMapper).map(dto, PokemonEntity.class);
    }

    @Test
    void GivenEmptyDto_MapToEntity_ReturnsEmptyEntity() {
        /* ***************** ARRANGE ***************** */
        PokemonDTO dto = new PokemonDTO();
        PokemonEntity entity = new PokemonEntity();

        when(modelMapper.map(any(), any())).thenReturn(entity);

        /* ***************** ACT ***************** */
        PokemonEntity result = pokemonMapper.mapToEntity(dto);

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result.getPokemonId()).isNull();
        assertThat(result.getPokedexId()).isNull();
        assertThat(result.getName()).isNull();
        assertThat(result.getType()).isNull();

        verify(modelMapper).map(dto, PokemonEntity.class);
    }

    @Test
    void GivenValidEntity_MapToDto_ReturnsMappedDto() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity entity = PokemonMockUtils.getMockedPikachuEntity(true);
//
//        /* ***************** ACT ***************** */
//        PokemonDTO resultDto = pokemonMapper.mapToDto(entity);
//
//        /* ***************** ASSERT ***************** */
//        assertThat(resultDto.getPokemonId()).isEqualTo(entity.getPokemonId());
//        assertThat(resultDto.getPokedexId()).isEqualTo(entity.getPokedexId());
//        assertThat(resultDto.getName()).isEqualTo(entity.getName());
//        assertThat(resultDto.getType()).isEqualTo(entity.getType());
    }

    @Test
    void GivenEmptyEntity_MapToDto_ReturnsEmptyDto() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity entity = new PokemonEntity();
        PokemonDTO dto = new PokemonDTO();

        when(modelMapper.map(any(), any())).thenReturn(dto);

        /* ***************** ACT ***************** */
        PokemonDTO result = pokemonMapper.mapToDto(entity);

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result.getPokemonId()).isNull();
        assertThat(result.getPokedexId()).isNull();
        assertThat(result.getName()).isNull();
        assertThat(result.getType()).isNull();
    }

    @Test
    void GivenEmptyDtoList_MapToEntities_ReturnsEmptyEntitiesList() {
        /* ***************** ARRANGE ***************** */
        when(modelMapper.map(any(), any())).thenReturn(null);

        /* ***************** ACT ***************** */
        List<PokemonEntity> result = pokemonMapper.mapListToEntities(List.of());

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result).isEmpty();

        verify(modelMapper, never()).map(any(), any());
    }

    @Test
    public void GivenValidDtoList_MapToEntities_ReturnCorrectEntities() {
        /* ***************** ARRANGE ***************** */
        PokemonDTO pikachuDto = PokemonMockUtils.getMockedPikachuDto(true);
        PokemonDTO raichuDto = PokemonMockUtils.getMockedRaichuDto(true);

        PokemonEntity pikachuEntity = PokemonMockUtils.getMockedPikachuEntity(true);
        PokemonEntity raichuEntity = PokemonMockUtils.getMockedRaichuEntity(true);

        List<PokemonDTO> pokemonDtoList = List.of(pikachuDto, raichuDto);

        when(modelMapper.map(pikachuDto, PokemonEntity.class)).thenReturn(pikachuEntity);
        when(modelMapper.map(raichuDto, PokemonEntity.class)).thenReturn(raichuEntity);

        /* ***************** ACT ***************** */
        List<PokemonEntity> resultEntities = pokemonMapper.mapListToEntities(pokemonDtoList);

        /* ***************** ASSERT ***************** */
        assertNotNull(resultEntities);
        assertThat(resultEntities).isNotEmpty();
        assertThat(resultEntities).hasSize(2);
    }

    @Test
    void GivenEmptyEntitiesList_MapToEntities_ReturnsEmptyDtoList() {
        /* ***************** ARRANGE ***************** */
        when(modelMapper.map(any(), any())).thenReturn(null);

        /* ***************** ACT ***************** */
        List<PokemonDTO> result = pokemonMapper.mapListToDto(List.of());

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result).isEmpty();

        verify(modelMapper, never()).map(any(), any());
    }

//    @Test
//    public void PokemonMapper_MapToDtoList_ReturnCorrectDtoList() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity pikachu = PokemonMockUtils.getMockedPikachuEntity(true);
//        PokemonEntity raichu = PokemonMockUtils.getMockedRaichuEntity(true);
//
//        List<PokemonEntity> pokemonEntities = List.of(pikachu, raichu);
//
//        /* ***************** ACT ***************** */
//        List<PokemonDTO> resultDtos = pokemonMapper.mapListToDto(pokemonEntities);
//
//        /* ***************** ASSERT ***************** */
//        assertThat(resultDtos).isNotNull();
//        assertThat(resultDtos).isNotEmpty();
//        assertThat(resultDtos.size()).isEqualTo(pokemonEntities.size());
//        assertThat(resultDtos.getFirst().getPokemonId()).isEqualTo(pokemonEntities.getFirst().getPokemonId());
//        assertThat(resultDtos.getFirst().getPokedexId()).isEqualTo(pokemonEntities.getFirst().getPokedexId());
//        assertThat(resultDtos.getFirst().getName()).isEqualTo(pokemonEntities.getFirst().getName());
//        assertThat(resultDtos.getLast().getPokemonId()).isEqualTo(pokemonEntities.getLast().getPokemonId());
//        assertThat(resultDtos.getLast().getPokedexId()).isEqualTo(pokemonEntities.getLast().getPokedexId());
//        assertThat(resultDtos.getLast().getName()).isEqualTo(pokemonEntities.getLast().getName());
//    }
}