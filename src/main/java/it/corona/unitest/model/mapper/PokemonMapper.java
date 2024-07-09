package it.corona.unitest.model.mapper;

import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.model.entity.PokemonEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonMapper {
    private ModelMapper modelMapper;

    public List<PokemonDto> mapListToDto(List<PokemonEntity> entitiesList) {
        return entitiesList
                .stream()
                .map(entity -> modelMapper.map(entity, PokemonDto.class))
                .toList();
    }

    public List<PokemonEntity> mapListToEntities(List<PokemonDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, PokemonEntity.class))
                .toList();
    }

    public PokemonDto mapToDto(PokemonEntity entity) {
        return modelMapper.map(entity, PokemonDto.class);
    }

    public PokemonEntity mapToEntity(PokemonDto dto) {
        return modelMapper.map(dto, PokemonEntity.class);
    }
}
