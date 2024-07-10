package it.corona.unitest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends PokemonException {
    private static final String ERROR_MESSAGE = "No Pokémon was found";

    public PokemonNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
