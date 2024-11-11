package jsges.nails.DTO.servicios;

import lombok.Data;

@Data
public class TipoServicioDTO {
    
    private int id;
    private int codigo;
    private String denominacion;
    private String detalle;
    private int estado;

}
