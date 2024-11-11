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
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int codigo;

    @Column
    private String denominacion;

    @Column
    private String detalle;

    @Column
    private int estado;

    
    public void asEliminado() {
        this.setEstado(1);
    }


}
