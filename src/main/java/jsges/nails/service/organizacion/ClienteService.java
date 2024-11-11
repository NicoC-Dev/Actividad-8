package jsges.nails.service.organizacion;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.Mapper.ClienteMapper;
import jsges.nails.domain.organizacion.Cliente;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.organizacion.ClienteRepository;
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
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    

    @Override
    public List<Cliente> listar() {
        
        return clienteRepository.buscarNoEliminados();
    }


    @Override
    public Cliente buscarPorId(long id) {
        Cliente model = clienteRepository.findById(id).orElse(null);
        if(model == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        return model;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente registrarCliente(ClienteDTO dto) {
        Cliente model = ClienteMapper.toEntity(dto);
        model.setEstado(0);
        return guardar(model);
    }

    @Override
    public void eliminar(long id) {
        Cliente model = clienteRepository.findById(id).orElse(null);
        if(model == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        model.asEliminado();
        guardar(model);
    }

    @Override
    public List<Cliente> listar(String consulta) {
         return clienteRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<Cliente> getClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public Page<ClienteDTO> obtenerClientesPaginados(String consulta, int page, int size) {
        // Obtener la lista de clientes según la consulta
        List<Cliente> listado = listar(consulta); // Asumiendo que 'listar' es un método que ya existe en el servicio

        // Convertir la lista de Clientes a ClienteDTO
        List<ClienteDTO> listadoDTO = listado.stream()
                                            .map(ClienteMapper::toDTO)
                                            .collect(Collectors.toList());

        // Crear la paginación con la lista convertida
        Pageable pageable = PageRequest.of(page, size);
        return findPaginated(pageable, listadoDTO);
    }


    @Override
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

        return new PageImpl<>(list, pageable, clientes.size());
    }


    @Override
    public Cliente actualizar(long id, ClienteDTO dto) {
        Cliente model = clienteRepository.findById(id).orElse(null);
        if(model == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        model.setRazonSocial(dto.getRazonSocial());
        model.setLetra(dto.getLetra());
        model.setContacto(dto.getContacto());
        model.setCelular(dto.getCelular());
        model.setMail(dto.getMail());
        model.setFechaInicio(dto.getFechaInicio());
        model.setFechaNacimiento(dto.getFechaNacimiento());

        return guardar(model);
    }


}
