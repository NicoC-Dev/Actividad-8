package jsges.nails.repository.servicios;
import jsges.nails.domain.servicios.ItemServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemServicioRepository extends JpaRepository<ItemServicio, Integer> {

    @Query("select p from ItemServicio p  where p.estado=0 ")
    List<ItemServicio> buscarNoEliminados();



    @Query("SELECT p FROM ItemServicio p WHERE p.estado = 0 ")
    List<ItemServicio> buscarExacto();

    @Query("SELECT p FROM ItemServicio p WHERE p.estado = 0 AND p.servicio.id = :idServicio")
    List<ItemServicio> buscarPorServicio(@Param("idServicio") Integer idServicio);
}

