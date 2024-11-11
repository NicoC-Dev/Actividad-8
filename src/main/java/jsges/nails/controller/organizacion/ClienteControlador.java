package jsges.nails.controller.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.Mapper.ClienteMapper;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.service.organizacion.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")

public class ClienteControlador {
    
    @Autowired
    private IClienteService clienteServicio;

    @GetMapping({"/clientes"})
    public List<Cliente> getAll() {

        return clienteServicio.listar();
    }

    @GetMapping({"/clientesPageQuery"})
    public ResponseEntity<Page<ClienteDTO>> getItems(@RequestParam(defaultValue = "") String consulta,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "${max_page}") int size) {
        Page<ClienteDTO> clientesPage = clienteServicio.obtenerClientesPaginados(consulta, page, size);
        return ResponseEntity.ok().body(clientesPage);
    }


    @PostMapping("/clientes")
    public ClienteDTO agregar(@RequestBody ClienteDTO dto){
        return ClienteMapper.toDTO(clienteServicio.registrarCliente(dto));
    }


    @DeleteMapping("/clienteEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        clienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getPorId(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.buscarPorId(id));
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id,
                                              @RequestBody ClienteDTO modelRecibido){
        
        return ResponseEntity.ok(clienteServicio.actualizar(id, modelRecibido));
    }

}
