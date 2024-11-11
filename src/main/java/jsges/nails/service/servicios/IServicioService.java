package jsges.nails.service.servicios;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    public List<ServicioDTO> listar();

    Page<ServicioDTO> obtenerServiciosPaginados(String consulta, int page, int size);

    public ServicioDTO buscarPorId(Integer id);

    public Servicio guardar(Servicio model);

    public Page<ServicioDTO> findPaginated(Pageable pageable,List<ServicioDTO> servicios);

    public Page<Servicio> getServicios(Pageable pageable);

    public List<Servicio> listar(String consulta);

    Servicio RegistrearServicio(ServicioDTO model);

}
