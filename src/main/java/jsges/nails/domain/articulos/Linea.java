package jsges.nails.domain.articulos;

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
public class Linea {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String denominacion;

    @Column
    private int codigo;

    @Column
    private String detalle;

    @Column
    private int estado;

    @Column
    private String observacion;

    public void asEliminado() {
        this.setEstado(1);
    }

   
}

