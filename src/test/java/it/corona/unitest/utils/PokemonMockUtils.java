package it.corona.unitest.utils;

import it.corona.unitest.enums.PokemonEnum;
import it.corona.unitest.enums.PokemonType;
import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;

import java.util.Random;

public class PokemonMockUtils {

    private PokemonMockUtils() {
        super();
    }

    private static final Random random = new Random();

    /* ***************** ENTITY ***************** */
    public static PokemonEntity getMockedBulbasaurEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.BULBASAUR.getPokedexId())
                .name(PokemonEnum.BULBASAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonEntity getMockedIvisaurEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.IVISAUR.getPokedexId())
                .name(PokemonEnum.IVISAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonEntity getMockedVenusaurEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.IVISAUR.getPokedexId())
                .name(PokemonEnum.VENUSAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonEntity getMockedSquirtleEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.SQUIRTLE.getPokedexId())
                .name(PokemonEnum.SQUIRTLE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonEntity getMockedWartotleEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.SQUIRTLE.getPokedexId())
                .name(PokemonEnum.SQUIRTLE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonEntity getMockedBlastoiseEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.BLASTOISE.getPokedexId())
                .name(PokemonEnum.BLASTOISE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonEntity getMockedCharmanderEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARMANDER.getPokedexId())
                .name(PokemonEnum.CHARMANDER.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonEntity getMockedCharmeleonEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARMELEON.getPokedexId())
                .name(PokemonEnum.CHARMELEON.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonEntity getMockedCharizardEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARIZARD.getPokedexId())
                .name(PokemonEnum.CHARIZARD.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonEntity getMockedPikachuEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.PIKACHU.getPokedexId())
                .name(PokemonEnum.PIKACHU.getPokemonName())
                .type(PokemonType.ELECTRIC.getType())
                .build();
    }

    public static PokemonEntity getMockedRaichuEntity(boolean idRequired) {
        return PokemonEntity.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.RAICHU.getPokedexId())
                .name(PokemonEnum.RAICHU.getPokemonName())
                .type(PokemonType.ELECTRIC.getType())
                .build();
    }

    /* ***************** DTO ***************** */
    public static PokemonDTO getMockedBulbasaurDto(boolean idRequired) {
        PokemonDTO bulbasuaur = new PokemonDTO();
        bulbasuaur.setPokemonId(getRandomLong(idRequired));
        bulbasuaur.setPokedexId(PokemonEnum.BULBASAUR.getPokedexId());
        bulbasuaur.setName(PokemonEnum.BULBASAUR.getPokemonName());
        bulbasuaur.setType(PokemonType.GRASS.getType());
        return bulbasuaur;
    }

    public static PokemonDTO getMockedIvisaurDto(boolean idRequired) {
        PokemonDTO ivisaur = new PokemonDTO();
        ivisaur.setPokemonId(getRandomLong(idRequired));
        ivisaur.setPokedexId(PokemonEnum.IVISAUR.getPokedexId());
        ivisaur.setName(PokemonEnum.IVISAUR.getPokemonName());
        ivisaur.setType(PokemonType.GRASS.getType());
        return ivisaur;
    }

    public static PokemonDTO getMockedVenusaurDto(boolean idRequired) {
        PokemonDTO venusaur = new PokemonDTO();
        venusaur.setPokemonId(getRandomLong(idRequired));
        venusaur.setPokedexId(PokemonEnum.VENUSAUR.getPokedexId());
        venusaur.setName(PokemonEnum.VENUSAUR.getPokemonName());
        venusaur.setType(PokemonType.GRASS.getType());
        return venusaur;
    }

    public static PokemonDTO getMockedSquirtleDto(boolean idRequired) {
        PokemonDTO squirtle = new PokemonDTO();
        squirtle.setPokemonId(getRandomLong(idRequired));
        squirtle.setPokedexId(PokemonEnum.SQUIRTLE.getPokedexId());
        squirtle.setName(PokemonEnum.SQUIRTLE.getPokemonName());
        squirtle.setType(PokemonType.WATER.getType());
        return squirtle;
    }

    public static PokemonDTO getMockedWartotleDto(boolean idRequired) {
        PokemonDTO wartotle = new PokemonDTO();
        wartotle.setPokemonId(getRandomLong(idRequired));
        wartotle.setPokedexId(PokemonEnum.WARTOTLE.getPokedexId());
        wartotle.setName(PokemonEnum.WARTOTLE.getPokemonName());
        wartotle.setType(PokemonType.FIRE.getType());
        return wartotle;
    }

    public static PokemonDTO getMockedBlastoiseDto(boolean idRequired) {
        PokemonDTO blastoise = new PokemonDTO();
        blastoise.setPokemonId(getRandomLong(idRequired));
        blastoise.setPokedexId(PokemonEnum.BLASTOISE.getPokedexId());
        blastoise.setName(PokemonEnum.BLASTOISE.getPokemonName());
        blastoise.setType(PokemonType.WATER.getType());
        return blastoise;
    }

    public static PokemonDTO getMockedCharmanderDto(boolean idRequired) {
        PokemonDTO charmander = new PokemonDTO();
        charmander.setPokemonId(getRandomLong(idRequired));
        charmander.setPokedexId(PokemonEnum.CHARMANDER.getPokedexId());
        charmander.setName(PokemonEnum.CHARMANDER.getPokemonName());
        charmander.setType(PokemonType.FIRE.getType());
        return charmander;
    }

    public static PokemonDTO getMockedCharmeleonDto(boolean idRequired) {
        PokemonDTO charmeleon = new PokemonDTO();
        charmeleon.setPokemonId(getRandomLong(idRequired));
        charmeleon.setPokedexId(PokemonEnum.CHARMELEON.getPokedexId());
        charmeleon.setName(PokemonEnum.CHARMELEON.getPokemonName());
        charmeleon.setType(PokemonType.FIRE.getType());
        return charmeleon;
    }

    public static PokemonDTO getMockedCharizardDto(boolean idRequired) {
        PokemonDTO charizard = new PokemonDTO();
        charizard.setPokemonId(getRandomLong(idRequired));
        charizard.setPokedexId(PokemonEnum.CHARIZARD.getPokedexId());
        charizard.setName(PokemonEnum.CHARMELEON.getPokemonName());
        charizard.setType(PokemonType.FIRE.getType());
        return charizard;
    }

    public static PokemonDTO getMockedPikachuDto(boolean idRequired) {
        PokemonDTO pikachu = new PokemonDTO();
        pikachu.setPokemonId(getRandomLong(idRequired));
        pikachu.setPokedexId(PokemonEnum.PIKACHU.getPokedexId());
        pikachu.setName(PokemonEnum.PIKACHU.getPokemonName());
        pikachu.setType(PokemonType.ELECTRIC.getType());
        return pikachu;
    }

    public static PokemonDTO getMockedRaichuDto(boolean idRequired) {
        PokemonDTO raichu = new PokemonDTO();
        raichu.setPokemonId(getRandomLong(idRequired));
        raichu.setPokedexId(PokemonEnum.RAICHU.getPokedexId());
        raichu.setName(PokemonEnum.RAICHU.getPokemonName());
        raichu.setType(PokemonType.ELECTRIC.getType());
        return raichu;
    }

    public static Long getRandomLong(boolean idRequired) {
        return idRequired ? 1 + random.nextLong(1000) : null;
    }
}