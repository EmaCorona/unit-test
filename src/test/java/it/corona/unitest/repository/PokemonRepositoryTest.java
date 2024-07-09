package it.corona.unitest.repository;

import it.corona.unitest.model.entity.PokemonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

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

        PokemonEntity mockedCharmander = PokemonEntity.builder().name("Charmander").type("Fire").build();
        PokemonEntity mockedBulbasaur = PokemonEntity.builder().name("Bulbasaur").type("Grass").build();
        PokemonEntity mockedSquirtle = PokemonEntity.builder().name("Squirtle").type("Water").build();

        mockedPokemons.add(mockedCharmander);
        mockedPokemons.add(mockedBulbasaur);
        mockedPokemons.add(mockedSquirtle);

        /* ***************** ACT ***************** */
        List<PokemonEntity> savedPokemons = pokemonRepository.saveAll(mockedPokemons);

        /* ***************** ASSERT ***************** */
        assertThat(savedPokemons).isNotNull();
        assertThat(savedPokemons).isEqualTo(mockedPokemons);
        assertThat(savedPokemons.size()).isEqualTo(mockedPokemons.size()).isEqualTo(3);
        assertThat(savedPokemons.getFirst().getPokemonId()).isGreaterThan(0);
        assertThat(savedPokemons.getFirst().getName()).isEqualTo("Charmander");
    }
}