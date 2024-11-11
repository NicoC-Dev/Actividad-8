package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.Mapper.LineaMapper;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.articulos.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineaService implements ILineaService {

    @Autowired
    private LineaRepository modelRepository;

    @Autowired
    private LineaMapper mapper;

    @Override
    public List<Linea> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public Linea buscarPorId(long id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public Linea guardar(Linea model) {
        return modelRepository.save(model);
    }

    @Override
    public void eliminar(Linea model) {
        modelRepository.save(model);
    }

    

    @Override
    public Page<Linea> getLineas(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }

    @Override
    public Page<LineaDTO> findPaginated(Pageable pageable, List<LineaDTO> lineas) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LineaDTO> list = lineas.size() < startItem ? Collections.emptyList() : lineas.subList(startItem, Math.min(startItem + pageSize, lineas.size()));
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), lineas.size());
    }

    @Override
    public List<Linea> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }

    @Override
    public Linea newModel(LineaDTO modelDTO) {
        return modelRepository.save(mapper.toEntity(modelDTO));
        
    }

    // Métodos adicionales
    @Override
    public List<LineaDTO> listarDTOs() {
        return modelRepository.buscarNoEliminados().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeLineaConDenominacion(String denominacion) {
        return !buscar(denominacion).isEmpty();
    }

    @Override
    public void marcarComoEliminado(long id) {
        Linea model = modelRepository.findById(id).orElse(null);
        if(model == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        model.asEliminado();
        modelRepository.save(model);
    }

    @Override
    public LineaDTO buscarDTOPorId(long id) {
        Linea linea = buscarPorId(id);
        return linea != null ? mapper.toDto(linea) : null;
    }

    @Override
    public Linea actualizarLinea(long id, LineaDTO modelRecibido) {
        Linea model = buscarPorId(id);
        if (model != null) {
            model.setDenominacion(modelRecibido.getDenominacion());
            guardar(model);
        }
        return model;
    }

    @Override
    public Page<LineaDTO> findPaginated(PageRequest pageRequest, String consulta) {
        return modelRepository.buscarNoEliminados(consulta, pageRequest)
                .map(mapper::toDto);
    }
    // `map` convierte cada `Linea` a `LineaDTO`
}


