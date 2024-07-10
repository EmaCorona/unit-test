package it.corona.unitest.controller;

import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "/pokemon")
@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping("/create-pokemon")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemon) {
        PokemonDto savedPokemon = pokemonService.createPokemon(pokemon);
        return new ResponseEntity<>(savedPokemon, HttpStatus.CREATED);
    }

    @GetMapping("/find-all-pokemons")
    public ResponseEntity<List<PokemonDto>> findAllPokemon() {
        List<PokemonDto> pokemons = pokemonService.findAllPokemon();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }
}
