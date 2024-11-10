package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.articulos.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class LineaController {

    private static final Logger logger = LoggerFactory.getLogger(LineaController.class);

    @Autowired
    private ILineaService modelService;

    

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

    @PostMapping("/linea")
    public ResponseEntity<Linea> agregar(@RequestBody LineaDTO model) {
        return modelService.existeLineaConDenominacion(model.getDenominacion()) ?
               ResponseEntity.status(HttpStatus.CONFLICT).build() :
               ResponseEntity.ok(modelService.newModel(model));
        
    }

    @PutMapping("/lineaEliminar/{id}")
    public ResponseEntity<Linea> eliminar(@PathVariable Integer id) {
        Linea model = modelService.buscarPorId(id);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        modelService.marcarComoEliminado(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getPorId(@PathVariable Integer id) {
        LineaDTO model = modelService.buscarDTOPorId(id);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        return ResponseEntity.ok(model);
    }

    @PutMapping("/linea/{id}")
    public ResponseEntity<Linea> actualizar(@PathVariable Integer id,
                                            @RequestBody LineaDTO modelRecibido) {
        Linea updatedModel = modelService.actualizarLinea(id, modelRecibido);
        if (updatedModel == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        return ResponseEntity.ok(updatedModel);
    }
}
