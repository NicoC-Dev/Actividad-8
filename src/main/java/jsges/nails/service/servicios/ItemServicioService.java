package jsges.nails.service.servicios;

import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.repository.servicios.ItemServicioRepository;
import jsges.nails.repository.servicios.ServicioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServicioService implements IItemServicioService {


    @Autowired
    private ItemServicioRepository modelRepository;
    private final Logger log = LoggerFactory.getLogger(ItemServicioService.class);

    @Override
    public List<ItemServicio> listar() {
        System.out.println("2"); // Debug: Verificar el contenido
        List<ItemServicio> items = modelRepository.findAll();
        System.out.println("Items desde el repositorio: " + items); // Debug: Verificar el contenido
        return items;
    }

    @Override
    public ItemServicio buscarPorId(Integer id) {
        return null;
    }

    @Override
    public ItemServicio guardar(ItemServicio model) {
        return modelRepository.save(model);
    }

    @Override
    public Page<ItemServicio> findPaginated(Pageable pageable, List<ItemServicio> servicios) {
        return null;
    }

    @Override
    public Page<ItemServicio> getItemServicios(Pageable pageable) {
        return null;
    }
    @Override
    public List<ItemServicio> buscarPorServicio(Integer idServicio){

        return modelRepository.buscarPorServicio(idServicio);
    };
}
