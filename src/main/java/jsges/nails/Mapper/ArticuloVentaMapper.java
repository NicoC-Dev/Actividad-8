package jsges.nails.Mapper;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.repository.articulos.LineaRepository;

@Component
public class ArticuloVentaMapper {

    @Autowired
    private LineaRepository lineaRepository;
    public ArticuloVenta toEntity(ArticuloVentaDTO dto) {
        ArticuloVenta articuloVenta = new ArticuloVenta();
        Linea linea = lineaRepository.findById(dto.id).orElse(null);
        articuloVenta.setId(dto.getId());
        articuloVenta.setDenominacion(dto.getDenominacion());
        articuloVenta.setLinea(linea);
        return articuloVenta;
    }

    public ArticuloVentaDTO toDTO(ArticuloVenta model){
        ArticuloVentaDTO articuloVentaDTO = new ArticuloVentaDTO();
        articuloVentaDTO.setId(model.getId());
        articuloVentaDTO.setDenominacion(model.getDenominacion());
        articuloVentaDTO.setLinea_id(model.getLinea().getId());
        return articuloVentaDTO;
    }

    
}