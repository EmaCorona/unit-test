package it.corona.unitest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PokemonException extends RuntimeException {
    public PokemonException(String message) {
        super(message);
    }
}
