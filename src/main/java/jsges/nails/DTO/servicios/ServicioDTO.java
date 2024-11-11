package jsges.nails.DTO.servicios;


import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Data
public class ServicioDTO{

     
    private long id;
    private long cliente;
    private Timestamp fechaDocumento;
    private Set<ItemServicioDTO> listaItems = new HashSet<>();
    private Double total;
    private String clienteRazonSocial;
    
    public ServicioDTO() {

    }

    public ServicioDTO(Servicio elemento, List<ItemServicio>list) {

        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();

        list.forEach((model) -> {
           listaItems.add(new ItemServicioDTO(model));
        });
    }

    public ServicioDTO(Servicio elemento){

        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();


    }
}
