package jsges.nails.repository.articulos;

import jsges.nails.domain.articulos.Linea;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {

    @Query("select p from Linea p  where p.estado=0 order by p.denominacion")
    List<Linea> buscarNoEliminados();


    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    Page<Linea> buscarNoEliminados(@Param("consulta") String consulta, PageRequest pageRequest);



    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND  p.denominacion LIKE:consulta ORDER BY p.denominacion")
    List<Linea> buscarExacto(@Param("consulta") String consulta);

    List<Linea> findByDenominacionContaining(String consulta);
}
