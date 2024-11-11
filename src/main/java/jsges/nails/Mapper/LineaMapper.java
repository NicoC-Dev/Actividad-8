package jsges.nails.Mapper;


import org.springframework.stereotype.Component;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;



@Component
public class LineaMapper {
    
    public Linea toEntity(LineaDTO dto) {
        if (dto == null) {
            return null;
        }

        Linea linea = new Linea();
        
        linea.setId(dto.getId());
        linea.setCodigo(dto.getCodigo());
        linea.setDetalle(dto.getDetalle());
        linea.setDenominacion(dto.getDenominacion());
        linea.setObservacion(dto.getObservacion());
        
        return linea;
    }

    public LineaDTO toDto(Linea linea) {
        if (linea == null) {
            return null;
        }

        LineaDTO dto = new LineaDTO();

        dto.setId(linea.getId());
        dto.setCodigo(linea.getCodigo());
        dto.setDetalle(linea.getDetalle());
        dto.setDenominacion(linea.getDenominacion());
        dto.setObservacion(linea.getObservacion());
        
        return dto;
    }
}