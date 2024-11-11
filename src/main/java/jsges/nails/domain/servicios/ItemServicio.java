package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ItemServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int estado;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private TipoServicio tipoServicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Servicio servicio;

    private Double precio;

    public void asEliminado() {
        this.setEstado(1);
    }

    

    public ItemServicio(Servicio servicio ,TipoServicio tipo, Double precio,String observacion) {
        this.servicio = servicio;
        this.tipoServicio = tipo;
        this.precio = precio;
        this.observacion=observacion;
    }
    

    
}
