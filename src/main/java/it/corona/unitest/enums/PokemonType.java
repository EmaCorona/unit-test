package it.corona.unitest.enums;

import lombok.Getter;

@Getter
public enum PokemonType {

    FIGHTING("FIGHTING"),
    GROUND("GROUND"),
    ROCK("ROCK"),
    PSYCHIC("PSYCHIC"),
    FIRE("FIRE"),
    ELECTRIC("ELECTRIC"),
    POISON("POISON"),
    FAIRY("FAIRY"),
    BUG("BUG"),
    DRAGON("DRAGON"),
    WATER("WATER"),
    GRASS("GRASS"),
    GHOST("GHOST"),
    FLYING("FLYING"),
    ICE("ICE"),
    DARK("DARK"),
    NORMAL("NORMAL"),
    STEEL("STEEL");

    private final String type;

    PokemonType(String type) {
        this.type = type;
    }
}