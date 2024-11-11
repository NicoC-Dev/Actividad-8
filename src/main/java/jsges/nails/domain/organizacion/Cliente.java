package jsges.nails.domain.organizacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column
        private String razonSocial;

        @Column
        private int estado;

        @Column
        private String letra;

        @Column
        private String contacto;

        @Column
        private String celular;
        
        @Column
        private String mail;

        @Column
        private Date fechaInicio;

        @Column
        private Date fechaNacimiento;

        public void asEliminado() {
                this.setEstado(1);
        }
}
