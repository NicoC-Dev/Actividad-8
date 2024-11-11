package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.organizacion.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Servicio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private int estado;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn
        private Cliente cliente;

        @Column
        private Timestamp fechaRegistro;

        @Column
        private Timestamp fechaRealizacion;

        @Column
        private double total;


}
