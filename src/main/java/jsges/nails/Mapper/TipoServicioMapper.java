package jsges.nails.Mapper;

import org.springframework.stereotype.Component;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;

@Component
public class TipoServicioMapper {
    public TipoServicio toEntity(TipoServicioDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoServicio model = new TipoServicio();
        model.setId(dto.getId());
        model.setCodigo(dto.getCodigo());
        model.setDetalle(dto.getDetalle());
        model.setDenominacion(dto.getDenominacion());
        return model;
    }

    public TipoServicioDTO toDTO(TipoServicio model) {
        if (model == null) {
            return null;
        }
        TipoServicioDTO dto = new TipoServicioDTO();
        dto.setId(model.getId());
        dto.setCodigo(model.getCodigo());
        dto.setDetalle(model.getDetalle()); 
        dto.setDenominacion(model.getDenominacion());
        return dto;
    }
}
