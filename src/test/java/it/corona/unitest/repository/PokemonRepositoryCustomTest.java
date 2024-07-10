package it.corona.unitest.repository;

import it.corona.unitest.model.entity.PokemonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryCustomTest {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonRepositoryCustomTest(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Test
    public void PokemonRepository_FindByType_ReturnPokemonWithThatType() {
        /* ***************** ARRANGE ***************** */
        PokemonEntity charmander = PokemonEntity.builder().name("Charmander").type("Fire").build();
        PokemonEntity charmeleon = PokemonEntity.builder().name("Charmeleon").type("Fire").build();
        PokemonEntity charizard = PokemonEntity.builder().name("Charizard").type("Fire").build();

        pokemonRepository.save(charmander);
        pokemonRepository.save(charmeleon);
        pokemonRepository.save(charizard);

        /* ***************** ACT ***************** */
        List<PokemonEntity> firePokemons = pokemonRepository.findByType("Fire");

        /* ***************** ASSERT ***************** */
        assertThat(firePokemons).isNotNull();
        assertThat(firePokemons.size()).isEqualTo(3);
        assertThat(firePokemons.stream().allMatch(pokemon -> pokemon.getType().equals("Fire"))).isTrue();
    }
}