package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.TipoObjeto;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Linea extends TipoObjeto {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String denominacion;

    @Column
    private int estado;

    @Column(columnDefinition = "TEXT")
    String observacion;

    

   
}

