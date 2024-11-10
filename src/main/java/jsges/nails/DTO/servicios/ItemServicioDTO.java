package jsges.nails.DTO.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import lombok.Data;

@Data
public class ItemServicioDTO {
    private Integer id ;
    private String tipoServicio ;
    private Integer tipoServicioId ;
    private Double precio;
    private String observaciones;

    public ItemServicioDTO(ItemServicio model) {
        this.observaciones=model.getObservacion();
        this.precio=model.getPrecio();
        this.tipoServicio=model.getTipoServicio().getDenominacion();
        this.tipoServicioId=model.getTipoServicio().getId();
        this.id=model.getId();

    }

    public ItemServicioDTO() {

    }
}
