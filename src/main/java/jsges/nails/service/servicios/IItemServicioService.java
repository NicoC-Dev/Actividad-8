package jsges.nails.service.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IItemServicioService {
    public List<ItemServicio> listar();

    public ItemServicio buscarPorId(Integer id);

    public ItemServicio guardar(ItemServicio model);

    public Page<ItemServicio> findPaginated(Pageable pageable,List<ItemServicio> servicios);

    public Page<ItemServicio> getItemServicios(Pageable pageable);

    public List<ItemServicio> buscarPorServicio(Integer idServicio);
}
