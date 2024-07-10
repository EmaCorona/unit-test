package it.corona.unitest.repository;

import it.corona.unitest.model.entity.PokemonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
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
        PokemonEntity mockedPokemon = PokemonEntity.builder().name("Pikachu").type("Electro").build();

        /* ***************** ACT ***************** */
        PokemonEntity savedPokemon = pokemonRepository.save(mockedPokemon);

        /* ***************** ASSERT ***************** */
        assertThat(savedPokemon).isNotNull();
        assertThat(savedPokemon).isEqualTo(mockedPokemon);
        assertThat(savedPokemon.getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemon.getName()).isEqualTo("Pikachu");
    }

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemons() {
        /* ***************** ARRANGE ***************** */
        List<PokemonEntity> mockedPokemons = new ArrayList<>();

        PokemonEntity charmander = PokemonEntity.builder().name("Charmander").type("Fire").build();
        PokemonEntity bulbasaur = PokemonEntity.builder().name("Bulbasaur").type("Grass").build();
        PokemonEntity squirtle = PokemonEntity.builder().name("Squirtle").type("Water").build();

        mockedPokemons.add(charmander);
        mockedPokemons.add(bulbasaur);
        mockedPokemons.add(squirtle);

        /* ***************** ACT ***************** */
        List<PokemonEntity> savedPokemons = pokemonRepository.saveAll(mockedPokemons);

        /* ***************** ASSERT ***************** */
        assertThat(savedPokemons).isNotNull();
        assertThat(savedPokemons).isEqualTo(mockedPokemons);
        assertThat(savedPokemons.size()).isEqualTo(mockedPokemons.size()).isEqualTo(3);
        assertThat(savedPokemons.getFirst().getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemons.getFirst().getName()).isEqualTo("Charmander");
    }

    @Test
    public void PokemonRepository_FindById_ReturnOnePokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonEntity.builder().name("Charmander").type("Fire").build();

        pokemonRepository.save(charmander);

        /* ***************** ACT ***************** */
        PokemonEntity returnedPokemon = pokemonRepository.findById(charmander.getPokemonId()).get();

        /* ***************** ASSERT ***************** */
        assertThat(returnedPokemon).isNotNull();
        assertThat(returnedPokemon.getName()).isEqualTo("Charmander");
        assertThat(returnedPokemon.getType()).isEqualTo("Fire");
    }

    @Test
    public void PokemonRepository_FindAll_ReturnsMoreThanOnePokemon() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonEntity.builder().name("Charmander").type("Fire").build();
        PokemonEntity bulbasaur = PokemonEntity.builder().name("Bulbasaur").type("Grass").build();
        PokemonEntity squirtle = PokemonEntity.builder().name("Squirtle").type("Water").build();

        pokemonRepository.save(charmander);
        pokemonRepository.save(bulbasaur);
        pokemonRepository.save(squirtle);

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
        PokemonEntity pokemon = PokemonEntity.builder().name("Charmander").type("Fire").build();
        pokemonRepository.save(pokemon);

        /* ***************** ACT ***************** */
        pokemon.setName("Charmeleon");
        pokemonRepository.save(pokemon);

        PokemonEntity updatedPokemon = pokemonRepository.findById(pokemon.getPokemonId()).get();

        /* ***************** ASSERT ***************** */
        assertThat(updatedPokemon).isNotNull();
        assertThat(updatedPokemon.getPokemonId()).isGreaterThan(0);
        assertThat(updatedPokemon.getName()).isEqualTo("Charmeleon");
        assertThat(updatedPokemon.getType()).isEqualTo("Fire");
    }

    @Test
    public void PokemonRepository_Delete_ReturnedPokemonIsEmpty() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity pokemon = PokemonEntity.builder().name("Charmander").type("Fire").build();
        pokemonRepository.save(pokemon);

        /* ***************** ACT ***************** */
        pokemonRepository.delete(pokemon);

        Optional<PokemonEntity> deletedPokemon = pokemonRepository.findById(pokemon.getPokemonId());

        /* ***************** ASSERT ***************** */
        assertThat(deletedPokemon).isEmpty();
        assertThat(deletedPokemon.isPresent()).isFalse();
    }
}