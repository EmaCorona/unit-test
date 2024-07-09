package it.corona.unitest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemon")
public class PokemonEntity {
    @Id
    @Column(name = "pokemon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pokemonId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}