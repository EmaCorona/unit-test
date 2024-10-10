package it.corona.unitest.model.mapper;

import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;

import java.util.List;


public interface PokemonMapper {
    List<PokemonDTO> mapListToDto(List<PokemonEntity> entitiesList);
    List<PokemonEntity> mapListToEntities(List<PokemonDTO> dtoList);
    PokemonDTO mapToDto(PokemonEntity entity);
    PokemonEntity mapToEntity(PokemonDTO dto);
}