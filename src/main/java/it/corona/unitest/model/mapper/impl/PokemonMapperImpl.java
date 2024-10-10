package it.corona.unitest.model.mapper.impl;

import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;
import it.corona.unitest.model.mapper.PokemonMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PokemonMapperImpl implements PokemonMapper {

    private final ModelMapper modelMapper;

    @Override
    public List<PokemonDTO> mapListToDto(List<PokemonEntity> entitiesList) {
        return entitiesList.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<PokemonEntity> mapListToEntities(List<PokemonDTO> dtoList) {
        return dtoList.stream()
                .map(this::mapToEntity)
                .toList();
    }

    @Override
    public PokemonDTO mapToDto(PokemonEntity entity) {
        return modelMapper.map(entity, PokemonDTO.class);
    }

    @Override
    public PokemonEntity mapToEntity(PokemonDTO dto) {
        return modelMapper.map(dto, PokemonEntity.class);
    }
}
