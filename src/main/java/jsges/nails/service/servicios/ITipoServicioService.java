package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoServicioService {

    List<TipoServicio> listar();

    TipoServicio buscarPorId(int id);

    TipoServicio guardar(TipoServicio model);

    void eliminar(TipoServicio model);

    TipoServicio actualizar(int id, TipoServicio dto);

    List<TipoServicio> listar(String consulta);

    Page<TipoServicio> getTiposServicios(Pageable pageable);

    Page<TipoServicio> findPaginated(Pageable pageable,List<TipoServicio> tipoServicios);
    
    List<TipoServicio> buscar(String consulta);

    TipoServicio newModel(TipoServicioDTO model);

    void eliminar(int id);
}
 