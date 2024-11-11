package jsges.nails.service.servicios;

import jsges.nails.domain.servicios.ItemServicio;

import jsges.nails.repository.servicios.ItemServicioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServicioService implements IItemServicioService {


    @Autowired
    private ItemServicioRepository modelRepository;

    @Override
    public List<ItemServicio> listar() {
        return modelRepository.findAll();
        
    }

    @Override
    public ItemServicio buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
        
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
    }
}
