package jsges.nails.Mapper;

import org.springframework.stereotype.Component;

import jsges.nails.DTO.Organizacion.ClienteDTO;
import jsges.nails.domain.organizacion.Cliente;

@Component
public class ClienteMapper {

    
    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setRazonSocial(cliente.getRazonSocial());
        clienteDTO.setLetra(cliente.getLetra());
        clienteDTO.setContacto(cliente.getContacto());
        clienteDTO.setCelular(cliente.getCelular());
        clienteDTO.setMail(cliente.getMail());
        clienteDTO.setFechaInicio(cliente.getFechaInicio());
        clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
        return clienteDTO;
    }

    
    public static Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setRazonSocial(clienteDTO.getRazonSocial());
        cliente.setLetra(clienteDTO.getLetra());
        cliente.setContacto(clienteDTO.getContacto());
        cliente.setCelular(clienteDTO.getCelular());
        cliente.setMail(clienteDTO.getMail());
        cliente.setFechaInicio(clienteDTO.getFechaInicio());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        return cliente;
    }
}