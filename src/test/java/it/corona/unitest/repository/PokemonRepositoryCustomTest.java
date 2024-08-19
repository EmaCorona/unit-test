package it.corona.unitest.repository;

import it.corona.unitest.enums.PokemonType;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.utils.PokemonMockUtils;
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
        PokemonEntity charmander = PokemonMockUtils.getMockedCharmanderEntity(false);
        PokemonEntity charmeleon = PokemonMockUtils.getMockedCharmeleonEntity(false);
        PokemonEntity charizard = PokemonMockUtils.getMockedCharizardEntity(false);

        List<PokemonEntity> pokemonToSave = List.of(charmander, charmeleon, charizard);

        pokemonRepository.saveAll(pokemonToSave);

        /* ***************** ACT ***************** */
        List<PokemonEntity> firePokemons = pokemonRepository.findByType(PokemonType.FIRE.getType());

        /* ***************** ASSERT ***************** */
        assertThat(firePokemons).isNotNull();
        assertThat(firePokemons.size()).isEqualTo(3);
        assertThat(firePokemons.stream().allMatch(pokemon -> pokemon.getType().equals(PokemonType.FIRE.getType()))).isTrue();
    }
}