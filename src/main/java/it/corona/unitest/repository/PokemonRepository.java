package it.corona.unitest.repository;

import it.corona.unitest.model.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    List<PokemonEntity> findByType(String type);
    boolean existsByName(String pokemonName);
}