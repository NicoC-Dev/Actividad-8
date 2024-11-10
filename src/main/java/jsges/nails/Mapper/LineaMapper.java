package jsges.nails.Mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;

@Mapper(componentModel = "spring")
@Component
public interface LineaMapper {
    
    Linea toEntity(LineaDTO dto);

    LineaDTO toDto(Linea linea);
}
