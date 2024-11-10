package jsges.nails.repository.servicios;
import jsges.nails.domain.servicios.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

    @Query("select p from TipoServicio p  where p.estado=0 order by p.denominacion")
    List<TipoServicio> buscarNoEliminados();


    @Query("SELECT p FROM TipoServicio p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<TipoServicio> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM TipoServicio p WHERE p.estado = 0 AND  p.denominacion LIKE:consulta ORDER BY p.denominacion")
    List<TipoServicio> buscarExacto(@Param("consulta") String consulta);

    List<TipoServicio> findByDenominacionContaining(String consulta);
}
