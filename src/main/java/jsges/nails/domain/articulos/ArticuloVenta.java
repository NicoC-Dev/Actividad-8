package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticuloVenta {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "TEXT")
        private String denominacion;

        @Column
        private int estado;

        @Column(columnDefinition = "TEXT")
        private String observacion;

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "linea_id") 
        private Linea linea;



        public void asEliminado() {
               this.setEstado(1);
        }
}

