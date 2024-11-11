package jsges.nails.controller.services;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.service.servicios.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ServicioController {
    

    @Autowired
    private IServicioService modelService;
    

    @GetMapping({"/servicios"})
    public List<ServicioDTO> getAll() {
        List<ServicioDTO> servicios = this.modelService.listar();
        
        return servicios;
        
    }
    @GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.buscarPorId(id));
    }



    @GetMapping({"/serviciosPageQuery"})
    public ResponseEntity<Page<ServicioDTO>> getItems(@RequestParam(defaultValue = "") String consulta, 
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "${max_page}") int size) {
        // Llamamos al servicio para obtener la página de Servicios con los DTOs convertidos y la paginación aplicada
        Page<ServicioDTO> serviciosPage = modelService.obtenerServiciosPaginados(consulta, page, size);
        return ResponseEntity.ok().body(serviciosPage);
    }


    @PostMapping("/servicios")
    public Servicio agregar(@RequestBody ServicioDTO model){

        return modelService.RegistrearServicio(model);
    }
}

