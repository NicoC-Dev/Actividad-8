package jsges.nails.controller.services;
import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.service.organizacion.IClienteService;
import jsges.nails.service.servicios.IItemServicioService;
import jsges.nails.service.servicios.IServicioService;
import jsges.nails.service.servicios.ITipoServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ServicioController {
    private static final Logger logger = LoggerFactory.getLogger(ServicioController.class);
    @Autowired
    private IServicioService modelService;
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ITipoServicioService tipoServicioService;

    @Autowired
    private IItemServicioService itemServicioService;

    public ServicioController() {

    }
    @GetMapping({"/servicios"})
    public List<ServicioDTO> getAll() {
        List<Servicio> servicios = this.modelService.listar();
        List<ServicioDTO> lista =new ArrayList<>();
        for (Servicio elemento : servicios) {
            System.out.println("1"); // Debug: Verificar el contenido
            List<ItemServicio> items = itemServicioService.listar();
            System.out.println("3"); // Debug: Verificar el contenido
            ServicioDTO ser  = new ServicioDTO(elemento,items);
            lista.add(ser);
        }
        return lista;
    }
    @GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> getPorId(@PathVariable Integer id){
        logger.info("entra  en buscar servicio"  );
        Servicio model = modelService.buscarPorId(id);
        if(model == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        List<ItemServicio>listItems = itemServicioService.buscarPorServicio(model.getId());
        ServicioDTO modelDTO  = new ServicioDTO(model,listItems);
        logger.info(modelDTO.toString());
        return ResponseEntity.ok(modelDTO);
    }




    @GetMapping({"/serviciosPageQuery"})
    public ResponseEntity<Page<ServicioDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "${max_page}") int size) {
        List<Servicio> listado = modelService.listar(consulta);
        List<ServicioDTO> listadoDTO    =  new ArrayList<>();
        listado.forEach((model) -> {
            listadoDTO.add(new ServicioDTO(model));
        });
        Page<ServicioDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listadoDTO);
        return ResponseEntity.ok().body(bookPage);
    }

    @PostMapping("/servicios")
    public Servicio agregar(@RequestBody ServicioDTO model){

        Integer idCliente = model.cliente;

        Servicio newModel =  new Servicio();
        newModel.setCliente(clienteService.buscarPorId(idCliente));
        newModel.setFechaRegistro(model.fechaDocumento);
        newModel.setFechaRealizacion(model.fechaDocumento);
        newModel.setEstado(0);

        Servicio servicioGuardado= modelService.guardar(newModel);
        for (ItemServicioDTO elemento : model.listaItems) {
            double precio = elemento.getPrecio();
            logger.info("entra for");

            TipoServicio tipoServicio = tipoServicioService.buscarPorId(elemento.getTipoServicioId());
            String observacion = elemento.getObservaciones();
            ItemServicio item = new ItemServicio(newModel, tipoServicio, precio,observacion);

            itemServicioService.guardar(item);

        }

        return servicioGuardado;
    }
}

