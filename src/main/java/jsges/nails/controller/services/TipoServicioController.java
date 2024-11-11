package jsges.nails.controller.services;


import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.service.servicios.ITipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class TipoServicioController {

    @Autowired
    private ITipoServicioService modelService;



    @GetMapping({"/tiposServicios"})
    public ResponseEntity<List<TipoServicio>> getAll() {
        return ResponseEntity.ok(modelService.listar());
    }

    @GetMapping({"/tiposServiciosPageQuery"})
    public ResponseEntity<Page<TipoServicio>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "${max_page}") int size) {

        Page<TipoServicio> bookPage = modelService.findPaginated(PageRequest.of(page, size),modelService.listar(consulta));
        return ResponseEntity.ok().body(bookPage);
    }


    @PostMapping("/tiposServicios/agregar")
    public ResponseEntity<TipoServicio> agregar(@RequestBody TipoServicioDTO dto) {
        return ResponseEntity.ok(modelService.newModel(dto));
    
    }


    @DeleteMapping("/tipoServicioEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        modelService.eliminar(id);
        return ResponseEntity.noContent().build();
        
    }

    @GetMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicio> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }

    @PutMapping("/tiposServicios/actualizar/{id}")
    public ResponseEntity<TipoServicio> actualizar(@PathVariable Integer id,
                                                   @RequestBody TipoServicio modelRecibido){
        
        return ResponseEntity.ok(modelService.actualizar(id, modelRecibido));
        
    }

}

