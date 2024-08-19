package it.corona.unitest.repository;

import it.corona.unitest.enums.PokemonEnum;
import it.corona.unitest.enums.PokemonType;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.utils.PokemonMockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTest {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonRepositoryTest(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Test
    public void PokemonRepository_Save_ReturnSavedPokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pokemon = PokemonMockUtils.getMockedPikachuEntity();

        /* ***************** ACT ***************** */
        PokemonEntity savedPokemon = pokemonRepository.save(pokemon);

        /* ***************** ASSERT ***************** */
        assertThat(savedPokemon).isNotNull();
        assertThat(savedPokemon.getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemon.getPokedexId()).isEqualTo(PokemonEnum.PIKACHU.getPokedexId());
        assertThat(savedPokemon.getName()).isEqualTo(PokemonEnum.PIKACHU.getPokemonName());
        assertThat(savedPokemon.getType()).isEqualTo(PokemonType.ELECTRIC.getType());
    }

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemons() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonMockUtils.getMockedCharmanderEntity();
        PokemonEntity bulbasaur = PokemonMockUtils.getMockedBulbasaurEntity();
        PokemonEntity squirtle = PokemonMockUtils.getMockedSquirtleEntity();

        List<PokemonEntity> mockedPokemons = List.of(charmander, squirtle, bulbasaur);

        /* ***************** ACT ***************** */
        List<PokemonEntity> savedPokemons = pokemonRepository.saveAll(mockedPokemons);

        /* ***************** ASSERT ***************** */
        assertThat(savedPokemons).isNotNull();
        assertThat(savedPokemons.size()).isEqualTo(mockedPokemons.size()).isEqualTo(3);
        assertThat(savedPokemons.getFirst().getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemons.getFirst().getName()).isEqualTo(PokemonEnum.CHARMANDER.getPokemonName());
        assertThat(savedPokemons.getFirst().getPokedexId()).isEqualTo(PokemonEnum.CHARMANDER.getPokedexId());
        assertThat(savedPokemons.getFirst().getType()).isEqualTo(PokemonType.FIRE.getType());
    }

    @Test
    public void PokemonRepository_FindById_ReturnOnePokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonMockUtils.getMockedCharmanderEntity();

        pokemonRepository.save(charmander);

        /* ***************** ACT ***************** */
        PokemonEntity returnedPokemon = pokemonRepository.findById(charmander.getPokemonId()).get();

        /* ***************** ASSERT ***************** */
        assertThat(returnedPokemon).isNotNull();
        assertThat(returnedPokemon.getName()).isEqualTo(PokemonEnum.CHARMANDER.getPokemonName());
        assertThat(returnedPokemon.getPokedexId()).isEqualTo(PokemonEnum.CHARMANDER.getPokedexId());
        assertThat(returnedPokemon.getType()).isEqualTo(PokemonType.FIRE.getType());
    }

    @Test
    public void PokemonRepository_FindAll_ReturnsAllPokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonMockUtils.getMockedCharmanderEntity();
        PokemonEntity bulbasaur = PokemonMockUtils.getMockedBulbasaurEntity();
        PokemonEntity squirtle = PokemonMockUtils.getMockedSquirtleEntity();

        List<PokemonEntity> pokemonToSave = List.of(charmander, bulbasaur, squirtle);

        pokemonRepository.saveAll(pokemonToSave);

        /* ***************** ACT ***************** */
        List<PokemonEntity> pokemonList = pokemonRepository.findAll();

        /* ***************** ASSERT ***************** */
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.size()).isGreaterThan(0);
        assertThat(pokemonList.size()).isEqualTo(3);
    }

    @Test
    public void PokemonRepository_Update_ReturnUpdatedPokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pokemon = PokemonMockUtils.getMockedPikachuEntity();
        pokemonRepository.save(pokemon);

        /* ***************** ACT ***************** */
        pokemon.setName(PokemonEnum.RAICHU.getPokemonName());
        pokemon.setPokedexId(PokemonEnum.RAICHU.getPokedexId());

        pokemonRepository.save(pokemon);

        PokemonEntity updatedPokemon = pokemonRepository.findById(pokemon.getPokemonId()).get();

        /* ***************** ASSERT ***************** */
        assertThat(updatedPokemon).isNotNull();
        assertThat(updatedPokemon.getPokemonId()).isGreaterThan(0);
        assertThat(updatedPokemon.getName()).isEqualTo(PokemonEnum.RAICHU.getPokemonName());
        assertThat(updatedPokemon.getPokedexId()).isEqualTo(PokemonEnum.RAICHU.getPokedexId());
        assertThat(updatedPokemon.getType()).isEqualTo(PokemonType.ELECTRIC.getType());
    }

    @Test
    public void PokemonRepository_Delete_ReturnedEmptyPokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pokemon = PokemonMockUtils.getMockedBlastoiseEntity();
        pokemonRepository.save(pokemon);

        /* ***************** ACT ***************** */
        pokemonRepository.delete(pokemon);

        Optional<PokemonEntity> deletedPokemon = pokemonRepository.findById(pokemon.getPokemonId());

        /* ***************** ASSERT ***************** */
        assertThat(deletedPokemon).isEmpty();
        assertThat(deletedPokemon.isPresent()).isFalse();
    }
}