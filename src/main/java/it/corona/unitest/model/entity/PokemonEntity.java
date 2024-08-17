package it.corona.unitest.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemon")
public class PokemonEntity {
    @Id
    @Column(name = "pokemon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pokemonId;

    @Column(name = "pokedex_id")
    private Long pokedexId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}