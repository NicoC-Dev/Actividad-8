package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.repository.organizacion.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    @Override
    public List<Cliente> listar() {
        return clienteRepository.buscarNoEliminados();
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        Cliente model = clienteRepository.findById(id).orElse(null);
        return model;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
          clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listar(String consulta) {
         return clienteRepository.buscarNoEliminados(consulta);
    }

    public Page<Cliente> getClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<ClienteDTO> findPaginated(Pageable pageable, List<ClienteDTO> clientes) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ClienteDTO> list;
        if (clientes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientes.size());
            list = clientes.subList(startItem, toIndex);
        }

        Page<ClienteDTO> bookPage
                = new PageImpl<ClienteDTO>(list, PageRequest.of(currentPage, pageSize), clientes.size());

        return bookPage;
    }


}
