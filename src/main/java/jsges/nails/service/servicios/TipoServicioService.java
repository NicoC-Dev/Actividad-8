package jsges.nails.service.servicios;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.Mapper.TipoServicioMapper;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TipoServicioService implements ITipoServicioService {

    @Autowired
    private TipoServicioRepository modelRepository;


    @Autowired
    private TipoServicioMapper mapper;

    @Override
    public List<TipoServicio> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public TipoServicio buscarPorId(int id) {
        TipoServicio tipoServicio = modelRepository.findById(id).orElse(null);
        if(tipoServicio == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }   

        return tipoServicio;

    }

    @Override
    public TipoServicio guardar(TipoServicio model) {
        return modelRepository.save(model);
    }


    @Override
    public TipoServicio newModel(TipoServicioDTO modelDTO) {
        TipoServicio model = mapper.toEntity(modelDTO);
        return guardar(model);
    }


    @Override
    public void eliminar(TipoServicio model) {

        modelRepository.save(model);
    }

    @Override
    public List<TipoServicio> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<TipoServicio> getTiposServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<TipoServicio> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


    @Override
    public Page<TipoServicio> findPaginated(Pageable pageable, List<TipoServicio>lineas) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TipoServicio> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        Page<TipoServicio> bookPage
                = new PageImpl<TipoServicio>(list, PageRequest.of(currentPage, pageSize), lineas.size());

        return bookPage;
    }

    

    @Override
    public TipoServicio actualizar(int id, TipoServicio modelRecibido) {
        TipoServicio model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        model.setCodigo(modelRecibido.getCodigo());
        model.setDenominacion(modelRecibido.getDenominacion());
        model.setDetalle(modelRecibido.getDetalle());
        
        return guardar(model);
    }

    @Override
    public void eliminar(int id) {
       TipoServicio tipoServicio = modelRepository.findById(id).orElse(null);

       if(tipoServicio == null){
           throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
       }
       tipoServicio.asEliminado();

       modelRepository.save(tipoServicio);
    }

}
 