package it.corona.unitest.model.mapper;

import it.corona.unitest.model.dto.PokemonDTO;
import it.corona.unitest.model.entity.PokemonEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PokemonMapper {

    private final ModelMapper modelMapper;

    public List<PokemonDTO> mapListToDto(List<PokemonEntity> entitiesList) {
        return entitiesList
                .stream()
                .map(entity -> modelMapper.map(entity, PokemonDTO.class))
                .toList();
    }

    public List<PokemonEntity> mapListToEntities(List<PokemonDTO> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, PokemonEntity.class))
                .toList();
    }

    public PokemonDTO mapToDto(PokemonEntity entity) {
        return modelMapper.map(entity, PokemonDTO.class);
    }

    public PokemonEntity mapToEntity(PokemonDTO dto) {
        return modelMapper.map(dto, PokemonEntity.class);
    }
}
