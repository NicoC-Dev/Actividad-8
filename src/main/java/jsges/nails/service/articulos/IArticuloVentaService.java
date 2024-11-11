package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloVentaService {

    List<ArticuloVentaDTO> listar();

    ArticuloVenta buscarPorId(long id);

    ArticuloVentaDTO guardar(ArticuloVentaDTO dto);

    ArticuloVentaDTO actualizar(long id, ArticuloVentaDTO dto);

    void eliminar(long id);

    List<ArticuloVenta> listar(String consulta);

    Page<ArticuloVenta> getArticulos(Pageable pageable);

    Page<ArticuloVentaDTO> findPaginated(PageRequest pageRequest, List<ArticuloVentaDTO> listado);
}
