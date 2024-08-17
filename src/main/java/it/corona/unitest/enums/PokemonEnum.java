package it.corona.unitest.enums;

import lombok.Getter;

@Getter
public enum PokemonEnum {

    BULBASAUR(1L, "Bulbasaur"),
    IVISAUR(2L, "Ivisaur"),
    VENUSAUR(3L, "Venusaur"),
    SQUIRTLE(4L, "Squirtle"),
    WARTOTLE(5L, "Wartotle"),
    BLASTOISE(6L, "Blastoise"),
    CHARMANDER(7L, "Charmander"),
    CHARMELEON(8L, "Charmeleon"),
    CHARIZARD(9L, "Charizard"),
    PIKACHU(10L, "Pikachu"),
    RAICHU(11L, "Raichu");

    private final long pokedexId;
    private final String pokemonName;

    PokemonEnum(long pokedexId, String pokemonName) {
        this.pokemonName = pokemonName;
        this.pokedexId = pokedexId;
    }
}