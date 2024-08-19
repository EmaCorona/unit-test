package it.corona.unitest.utils;

import it.corona.unitest.enums.PokemonEnum;
import it.corona.unitest.enums.PokemonType;
import it.corona.unitest.model.dto.PokemonDto;
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
    public static PokemonDto getMockedBulbasaurDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.BULBASAUR.getPokedexId())
                .name(PokemonEnum.BULBASAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonDto getMockedIvisaurDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.IVISAUR.getPokedexId())
                .name(PokemonEnum.IVISAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonDto getMockedVenusaurDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.IVISAUR.getPokedexId())
                .name(PokemonEnum.VENUSAUR.getPokemonName())
                .type(PokemonType.GRASS.getType())
                .build();
    }

    public static PokemonDto getMockedSquirtleDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.SQUIRTLE.getPokedexId())
                .name(PokemonEnum.SQUIRTLE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonDto getMockedWartotleDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.SQUIRTLE.getPokedexId())
                .name(PokemonEnum.SQUIRTLE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonDto getMockedBlastoiseDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.BLASTOISE.getPokedexId())
                .name(PokemonEnum.BLASTOISE.getPokemonName())
                .type(PokemonType.WATER.getType())
                .build();
    }

    public static PokemonDto getMockedCharmanderDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARMANDER.getPokedexId())
                .name(PokemonEnum.CHARMANDER.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonDto getMockedCharmeleonDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARMELEON.getPokedexId())
                .name(PokemonEnum.CHARMELEON.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonDto getMockedCharizardDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.CHARIZARD.getPokedexId())
                .name(PokemonEnum.CHARIZARD.getPokemonName())
                .type(PokemonType.FIRE.getType())
                .build();
    }

    public static PokemonDto getMockedPikachuDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.PIKACHU.getPokedexId())
                .name(PokemonEnum.PIKACHU.getPokemonName())
                .type(PokemonType.ELECTRIC.getType())
                .build();
    }

    public static PokemonDto getMockedRaichuDto(boolean idRequired) {
        return PokemonDto.builder()
                .pokemonId(getRandomLong(idRequired))
                .pokedexId(PokemonEnum.RAICHU.getPokedexId())
                .name(PokemonEnum.RAICHU.getPokemonName())
                .type(PokemonType.ELECTRIC.getType())
                .build();
    }

    public static Long getRandomLong(boolean idRequired) {
        return idRequired ? 1 + random.nextLong(1000) : null;
    }
}