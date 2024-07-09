package it.corona.unitest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {
    private Long pokemonId;
    private String name;
    private String type;
}
