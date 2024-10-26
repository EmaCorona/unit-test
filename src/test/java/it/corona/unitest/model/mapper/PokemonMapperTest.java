package it.corona.unitest.model.mapper;

import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.impl.PokemonMapperImpl;
import it.corona.unitest.utils.PokemonMockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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

    @Spy
    @InjectMocks
    private PokemonMapperImpl pokemonMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void GivenValidDto_MapToEntities_ReturnsMappedEntity() {
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
        /* ***************** ARRANGE ***************** */
        PokemonEntity entity = PokemonMockUtils.getMockedPikachuEntity(false);
        PokemonDTO dto = PokemonMockUtils.getMockedPikachuDto(false);

        when(modelMapper.map(entity, PokemonDTO.class)).thenReturn(dto);

        /* ***************** ACT ***************** */
        PokemonDTO resultDTO = pokemonMapper.mapToDto(entity);

        /* ***************** ASSERT ***************** */
        assertNotNull(resultDTO);
        assertEquals(resultDTO.getPokedexId(), entity.getPokedexId());
        assertEquals(resultDTO.getName(), entity.getName());
        assertEquals(resultDTO.getType(), entity.getType());

        verify(modelMapper).map(entity, PokemonDTO.class);
    }

    @Test
    void GivenEmptyEntity_MapToDto_ReturnsEmptyDto() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity entity = new PokemonEntity();
        PokemonDTO dto = new PokemonDTO();

        when(modelMapper.map(entity, PokemonDTO.class)).thenReturn(dto);

        /* ***************** ACT ***************** */
        PokemonDTO result = pokemonMapper.mapToDto(entity);

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result.getPokemonId()).isNull();
        assertThat(result.getPokedexId()).isNull();
        assertThat(result.getName()).isNull();
        assertThat(result.getType()).isNull();

        verify(modelMapper).map(entity, PokemonDTO.class);
    }

    @Test
    void GivenValidDtoList_MapToEntities_ReturnCorrectEntities() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pikachuEntity = PokemonMockUtils.getMockedPikachuEntity(true);
        PokemonEntity raichuEntity = PokemonMockUtils.getMockedRaichuEntity(true);

        List<PokemonEntity> pokemonEntityList = List.of(pikachuEntity, raichuEntity);

        doReturn(pokemonEntityList).when(pokemonMapper).mapListToEntities(anyList());

        /* ***************** ACT ***************** */
        List<PokemonEntity> resultList = pokemonMapper.mapListToEntities(anyList());

        /* ***************** ASSERT ***************** */
        assertNotNull(resultList);
        assertThat(resultList).isNotEmpty();
        assertEquals(resultList.getFirst().getPokedexId(), pikachuEntity.getPokedexId());
        assertEquals(resultList.size(), 2);

        verify(pokemonMapper).mapListToEntities(anyList());
    }

    @Test
    void GivenEmptyDtoList_MapToEntities_ReturnsEmptyEntities() {
        /* ***************** ARRANGE ***************** */
        doReturn(List.of()).when(pokemonMapper).mapListToEntities(any());

        /* ***************** ACT ***************** */
        List<PokemonEntity> result = pokemonMapper.mapListToEntities(anyList());

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result).isEmpty();

        verify(pokemonMapper).mapListToEntities(anyList());
    }

    @Test
    void GivenValidEntities_MapToEntities_ReturnCorrectEntities() {
        /* ***************** ARRANGE ***************** */
        PokemonDTO pikachu = PokemonMockUtils.getMockedPikachuDto(false);
        PokemonDTO raichu = PokemonMockUtils.getMockedRaichuDto(false);

        List<PokemonDTO> pokemonDtoList = List.of(pikachu, raichu);

        doReturn(pokemonDtoList).when(pokemonMapper).mapListToDto(anyList());

        /* ***************** ACT ***************** */
        List<PokemonDTO> resultList = pokemonMapper.mapListToDto(anyList());

        /* ***************** ASSERT ***************** */
        assertNotNull(resultList);
        assertThat(resultList).isNotEmpty();
        assertEquals(resultList.getFirst().getPokedexId(), pikachu.getPokedexId());
        assertEquals(resultList.size(), 2);

        verify(pokemonMapper).mapListToDto(anyList());
    }

    @Test
    void GivenEmptyEntities_MapToEntities_ReturnsEmptyDtoList() {
        /* ***************** ARRANGE ***************** */
        doReturn(List.of()).when(pokemonMapper).mapListToDto(any());

        /* ***************** ACT ***************** */
        List<PokemonDTO> result = pokemonMapper.mapListToDto(anyList());

        /* ***************** ASSERT ***************** */
        assertNotNull(result);
        assertThat(result).isEmpty();

        verify(pokemonMapper).mapListToDto(anyList());
    }
}