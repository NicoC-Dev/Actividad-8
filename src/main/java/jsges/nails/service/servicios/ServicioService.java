package jsges.nails.service.servicios;
import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.ServicioRepository;
import jsges.nails.service.organizacion.ClienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService implements IServicioService {
    

    @Autowired
    private ServicioRepository modelRepository;

    @Autowired
    private IItemServicioService itemServicioService;

    @Autowired
    private ITipoServicioService tipoServicioService;

    @Autowired
    private ClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ServicioService.class);

    @Override
    public List<ServicioDTO> listar() {
        List<Servicio> servicios = modelRepository.buscarNoEliminados();
        List<ServicioDTO> lista = new ArrayList<>();
        
        for (Servicio elemento : servicios) {
            List<ItemServicio> items = itemServicioService.listar();
            ServicioDTO servicioDTO = new ServicioDTO(elemento, items);
            lista.add(servicioDTO);
        }
        
        

        
        return lista;
    }

    @Override
    public ServicioDTO buscarPorId(int id) {
        Servicio servicio = modelRepository.findById(id).orElse(null);
        if (servicio == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
            
        }
        List<ItemServicio> listItems = itemServicioService.buscarPorServicio(servicio.getId());
        ServicioDTO modelDTO  = new ServicioDTO(servicio,listItems);
        logger.info(modelDTO.toString());
        return modelDTO;
    }

    @Override
    public Servicio guardar(Servicio model) {
        return modelRepository.save(model);
    }


    @Override
    public Page<Servicio> getServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

        @Override
    public Page<ServicioDTO> obtenerServiciosPaginados(String consulta, int page, int size) {
        // Obtener la lista de servicios según la consulta
        List<Servicio> listado = listar(consulta);

        // Convertir la lista de Servicios a ServicioDTO
        List<ServicioDTO> listadoDTO = listado.stream()
                                            .map(ServicioDTO::new)
                                            .collect(Collectors.toList());

        // Crear la paginación con la lista convertida
        Pageable pageable = PageRequest.of(page, size);
        return findPaginated(pageable, listadoDTO);
    }

        @Override
    public Page<ServicioDTO> findPaginated(Pageable pageable, List<ServicioDTO> listado) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<ServicioDTO> list;
        if (listado.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listado.size());
            list = listado.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, pageable, listado.size());
    }


    @Override
    public List<Servicio> listar(String consulta) {
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Servicio RegistrearServicio(ServicioDTO model) {
        double total = 0;
        long idCliente = model.getCliente();

        Servicio newModel = new Servicio();
        newModel.setCliente(clienteService.buscarPorId(idCliente));
        newModel.setFechaRegistro(model.getFechaDocumento());
        newModel.setFechaRealizacion(model.getFechaDocumento());
        newModel.setEstado(0);
        
        Servicio servicioGuardado = modelRepository.save(newModel);
        
        for (ItemServicioDTO elemento : model.getListaItems()) {
            double precio = elemento.getPrecio();
            logger.info("entra for");

            TipoServicio tipoServicio = tipoServicioService.buscarPorId(elemento.getTipoServicioId());
            String observacion = elemento.getObservaciones();
            ItemServicio item = new ItemServicio(newModel, tipoServicio, precio, observacion);
            total += precio;
            itemServicioService.guardar(item);

        }
        newModel.setTotal(total);
        return servicioGuardado;
    }

    @Override
    public void eliminar(int id) {
        Servicio model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
            
        }
        model.setEstado(1);
        guardar(model);
    }

}
