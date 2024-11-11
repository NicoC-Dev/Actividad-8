package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineaService {

    List<Linea> listar();

    Linea buscarPorId(long id);

    Linea guardar(Linea model);

    void eliminar(Linea model);

    Page<Linea> getLineas(Pageable pageable);

    Page<LineaDTO> findPaginated(Pageable pageable, List<LineaDTO> lineas);

    List<Linea> buscar(String consulta);

    Linea newModel(LineaDTO model);

    // Métodos adicionales para refactorización
    List<LineaDTO> listarDTOs();

    boolean existeLineaConDenominacion(String denominacion);

    void marcarComoEliminado(long id);

    LineaDTO buscarDTOPorId(long id);

    Linea actualizarLinea(long id, LineaDTO modelRecibido);

    Page<LineaDTO> findPaginated(PageRequest pageRequest, String consulta);
}
