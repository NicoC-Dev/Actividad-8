package jsges.nails.Mapper;

import javax.sound.sampled.Line;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;

@Mapper(componentModel = "spring")
@Component
public class LineaMapper {
    
    public Linea toEntity(LineaDTO dto){
        Linea linea = new Linea();
        linea.setId(dto.getId());
        linea.setDenominacion(dto.getDenominacion());
        return linea;
    }

    public LineaDTO toDTO(Linea model){
        LineaDTO lineaDTO = new LineaDTO();
        lineaDTO.setId(model.getId());
        lineaDTO.setDenominacion(model.getDenominacion());
        return lineaDTO;
    }
}
