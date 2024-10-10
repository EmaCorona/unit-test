package it.corona.unitest.service;

import it.corona.unitest.model.mapper.PokemonMapper;
import it.corona.unitest.repository.PokemonRepository;
import it.corona.unitest.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PokemonServiceTest {

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokemonMapper pokemonMapper;

    @Mock
    private PokemonRepository pokemonRepository;

//    @Test
//    public void PokemonService_CreatePokemon_ReturnsPokemonDto() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity entity = PokemonMockUtils.getMockedBlastoiseEntity(true);
//        PokemonDto dto = PokemonMockUtils.getMockedBlastoiseDto(true);
//
//        when(pokemonMapper.mapToEntity(any(PokemonDto.class))).thenReturn(entity);
//        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(entity);
//        when(pokemonMapper.mapToDto(any(PokemonEntity.class))).thenReturn(dto);
//
//        /* ***************** ACT ***************** */
//        PokemonDto savedPokemon = pokemonService.createPokemon(dto);
//
//        /* ***************** ASSERT ***************** */
//        verify(pokemonMapper).mapToEntity(dto);
//        verify(pokemonRepository).save(entity);
//        verify(pokemonMapper).mapToDto(entity);
//
//        assertThat(savedPokemon).isNotNull();
//        assertThat(savedPokemon.getPokemonId()).isGreaterThan(0);
//        assertThat(savedPokemon.getName()).isEqualTo(PokemonEnum.BLASTOISE.getPokemonName());
//        assertThat(savedPokemon.getPokedexId()).isEqualTo(PokemonEnum.BLASTOISE.getPokedexId());
//        assertThat(savedPokemon.getType()).isEqualTo(PokemonType.WATER.getType());
//    }
//
//    @Test
//    public void PokemonService_FindById_ReturnsOnePokemon() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity entity = PokemonMockUtils.getMockedCharmanderEntity(true);
//        PokemonDto dto = PokemonMockUtils.getMockedCharmanderDto(false);
//
//        dto.setPokemonId(entity.getPokemonId());
//
//        when(pokemonRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
//        when(pokemonMapper.mapToDto(any(PokemonEntity.class))).thenReturn(dto);
//
//        /* ***************** ACT ***************** */
//        PokemonDto result = pokemonService.findById(entity.getPokemonId());
//
//        /* ***************** ASSERT ***************** */
//        verify(pokemonRepository).findById(entity.getPokemonId());
//        verify(pokemonMapper).mapToDto(entity);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getPokemonId()).isGreaterThan(0);
//        assertThat(result.getPokemonId()).isEqualTo(entity.getPokemonId());
//        assertThat(result.getPokedexId()).isEqualTo(PokemonEnum.CHARMANDER.getPokedexId());
//        assertThat(result.getName()).isEqualTo(PokemonEnum.CHARMANDER.getPokemonName());
//        assertThat(result.getType()).isEqualTo(PokemonType.FIRE.getType());
//    }
//
//    @Test
//    public void PokemonService_FindAll_ReturnsAllPokemons() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity pikachuEntity = PokemonMockUtils.getMockedCharmanderEntity(false);
//        PokemonEntity raichuEntity = PokemonMockUtils.getMockedRaichuEntity(false);
//
//        List<PokemonEntity> pokemonEntities = List.of(pikachuEntity, raichuEntity);
//
//        PokemonDto pikachuDto = PokemonMockUtils.getMockedPikachuDto(false);
//        PokemonDto raichuDto = PokemonMockUtils.getMockedRaichuDto(false);
//
//        List<PokemonDto> pokemonDtos = List.of(pikachuDto, raichuDto);
//
//        when(pokemonRepository.findAll()).thenReturn(pokemonEntities);
//        when(pokemonMapper.mapListToDto(anyList())).thenReturn(pokemonDtos);
//
//        /* ***************** ACT ***************** */
//        List<PokemonDto> resultList = pokemonService.findAllPokemon();
//
//        /* ***************** ASSERT ***************** */
//        verify(pokemonRepository).findAll();
//        verify(pokemonMapper).mapListToDto(pokemonEntities);
//
//        assertThat(resultList).isNotNull();
//        assertThat(resultList).isNotEmpty();
//        assertThat(resultList.getFirst().getPokedexId()).isEqualTo(PokemonEnum.PIKACHU.getPokedexId());
//        assertThat(resultList.getFirst().getName()).isEqualTo(PokemonEnum.PIKACHU.getPokemonName());
//        assertThat(resultList.getFirst().getType()).isEqualTo(PokemonType.ELECTRIC.getType());
//        assertThat(resultList.getLast().getPokedexId()).isEqualTo(PokemonEnum.RAICHU.getPokedexId());
//        assertThat(resultList.getLast().getName()).isEqualTo(PokemonEnum.RAICHU.getPokemonName());
//        assertThat(resultList.getLast().getType()).isEqualTo(PokemonType.ELECTRIC.getType());
//    }
//
//    @Test
//    public void PokemonService_UpdatePokemon_ReturnUpdatedPokemon() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity pikachuEntity = PokemonMockUtils.getMockedPikachuEntity(true);
//        PokemonDto pikachuToRaichuDto = PokemonMockUtils.getMockedRaichuDto(false);
//
//        pikachuToRaichuDto.setPokemonId(pikachuEntity.getPokemonId());
//
//        when(pokemonRepository.findById(any(Long.class))).thenReturn(Optional.of(pikachuEntity));
//        when(pokemonRepository.save(any(PokemonEntity.class))).thenReturn(pikachuEntity);
//        when(pokemonMapper.mapToDto(any(PokemonEntity.class))).thenReturn(pikachuToRaichuDto);
//
//        /* ***************** ACT ***************** */
//        PokemonDto updatedPokemon = pokemonService.updatePokemon(pikachuToRaichuDto, pikachuEntity.getPokemonId());
//
//        /* ***************** ASSERT ***************** */
//        verify(pokemonRepository).findById(pikachuEntity.getPokemonId());
//        verify(pokemonRepository).save(pikachuEntity);
//        verify(pokemonMapper).mapToDto(pikachuEntity);
//
//        assertThat(updatedPokemon).isNotNull();
//        assertThat(updatedPokemon.getPokemonId()).isGreaterThan(0);
//        assertThat(updatedPokemon.getPokemonId()).isEqualTo(pikachuEntity.getPokemonId());
//        assertThat(updatedPokemon.getPokedexId()).isEqualTo(PokemonEnum.RAICHU.getPokedexId());
//        assertThat(updatedPokemon.getName()).isEqualTo(PokemonEnum.RAICHU.getPokemonName());
//        assertThat(updatedPokemon.getType()).isEqualTo(PokemonType.ELECTRIC.getType());
//    }
//
//    @Test
//    public void PokemonService_DeletePokemonById_ReturnOk() {
//        /* ***************** ARRANGE ***************** */
//        PokemonEntity entity = PokemonMockUtils.getMockedSquirtleEntity(true);
//
//        when(pokemonRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
//
//        /* ***************** ACT ***************** */
//        pokemonService.deletePokemonById(entity.getPokemonId());
//
//        /* ***************** ASSERT ***************** */
//        verify(pokemonRepository).findById(entity.getPokemonId());
//        verify(pokemonRepository).delete(entity);
//        verifyNoMoreInteractions(pokemonRepository); // Verifica che il Pokémon non sia più presente
//    }
}