package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    public List<Servicio> listar();

    public Servicio buscarPorId(Integer id);

    public Servicio guardar(Servicio model);

    public Page<ServicioDTO> findPaginated(Pageable pageable,List<ServicioDTO> servicios);

    public Page<Servicio> getServicios(Pageable pageable);

    public List<Servicio> listar(String consulta);

}
