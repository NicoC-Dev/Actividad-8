package jsges.nails.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.TipoServicioRepository;

@Component
public class ItemServicioMapper {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public static ItemServicioDTO toDto(ItemServicio model) {
        if (model == null) {
            return null;
        }
        ItemServicioDTO dto = new ItemServicioDTO();
        dto.setObservaciones(model.getObservacion());
        dto.setPrecio(model.getPrecio());
        dto.setTipoServicio(model.getTipoServicio().getDenominacion());
        dto.setTipoServicioId(model.getTipoServicio().getId());
        dto.setId(model.getId());

        return dto;
    }

    public ItemServicio toEntity(ItemServicioDTO dto) {
        if (dto == null) {
            return null;
        }

        ItemServicio entity = new ItemServicio();
        entity.setObservacion(dto.getObservaciones());
        entity.setPrecio(dto.getPrecio());

        // Obtiene el TipoServicio usando el tipoServicioId del DTO
        TipoServicio tipoServicio = tipoServicioRepository.findById(dto.getTipoServicioId()).orElse(null);
        if (tipoServicio == null) {
            throw new RecursoNoEncontradoExcepcion(null);
        }
        entity.setTipoServicio(tipoServicio);

        entity.setId(dto.getId());

        return entity;
    }
}
