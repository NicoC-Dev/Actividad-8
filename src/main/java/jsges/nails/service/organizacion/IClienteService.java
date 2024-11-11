package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> listar();

    Page<ClienteDTO> obtenerClientesPaginados(String consulta, int page, int size);

    Cliente actualizar(long id,ClienteDTO dto);

    Cliente registrarCliente(ClienteDTO dto);
    
    Cliente buscarPorId(long id);

    public Cliente guardar(Cliente cliente);

    public void eliminar(long id);

      public List<Cliente> listar(String consulta);

    public Page<Cliente> getClientes(Pageable pageable);

    public Page<ClienteDTO> findPaginated(Pageable pageable, List<ClienteDTO> clientes);
}
