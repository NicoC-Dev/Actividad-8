package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.Mapper.LineaMapper;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.repository.articulos.LineaRepository;
import jsges.nails.service.articulos.ILineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class LineaController {

    @Autowired
    private ILineaService modelService;

    @Autowired
    private LineaRepository modelRepository;

    
    @Autowired
    private LineaMapper mapper;

    @GetMapping("/lineas")
    public ResponseEntity<List<LineaDTO>> getAll() {
        return ResponseEntity.ok(modelService.listarDTOs());
    }

    @GetMapping("/lineasPageQuery")
    public ResponseEntity<Page<LineaDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "${max_page}") int size) {
                                                    
        return ResponseEntity.ok(modelService.findPaginated(PageRequest.of(page, size), consulta));
        
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getPorId(@PathVariable Integer id) {
        LineaDTO model = modelService.buscarDTOPorId(id);
        return ResponseEntity.ok(model);
    }

    @PostMapping("/linea")
    public ResponseEntity<Linea> agregar(@RequestBody LineaDTO model) {

        modelService.newModel(model);
        
        return ResponseEntity.ok(mapper.toEntity(model));
        
    }

    @DeleteMapping("/lineaEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        modelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/linea/{id}")
    public ResponseEntity<Linea> actualizar(@PathVariable Integer id,
                                            @RequestBody LineaDTO modelRecibido) {
        Linea updatedModel = modelService.actualizarLinea(id, modelRecibido);
        
        return ResponseEntity.ok(updatedModel);
    }
}
