package jsges.nails.Service;

import jsges.nails.DTO.servicios.ItemServicioDTO;
import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.ItemServicio;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.servicios.ServicioRepository;
import jsges.nails.service.organizacion.ClienteService;
import jsges.nails.service.servicios.IItemServicioService;
import jsges.nails.service.servicios.ITipoServicioService;
import jsges.nails.service.servicios.ServicioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ServicioServiceTest {

    @Mock
    private ServicioRepository modelRepository;

    @Mock
    private IItemServicioService itemServicioService;

    @Mock
    private ITipoServicioService tipoServicioService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ServicioService servicioService;

    private Servicio servicio;
    private ServicioDTO servicioDTO;
    private ItemServicio itemServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        servicio = new Servicio();
        servicio.setId(1);
        servicioDTO = new ServicioDTO();
        
        itemServicio = new ItemServicio();
        itemServicio.setPrecio(100.2);
    }
    

    @Test
    void testBuscarPorIdNotFound() {
        when(modelRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(RecursoNoEncontradoExcepcion.class, () -> servicioService.buscarPorId(1));
    }

    @Test
    void testGuardar() {
        when(modelRepository.save(servicio)).thenReturn(servicio);
        
        Servicio result = servicioService.guardar(servicio);
        
        assertNotNull(result);
        assertEquals(servicio.getId(), result.getId());
    }

    @Test
    void testGetServicios() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Servicio> servicioPage = new PageImpl<>(List.of(servicio), pageable, 1);
        when(modelRepository.findAll(pageable)).thenReturn(servicioPage);

        Page<Servicio> result = servicioService.getServicios(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(servicio.getId(), result.getContent().get(0).getId());
    }

    

    @Test
    void testRegistrearServicio() {
        servicioDTO.setCliente(1L);
        ItemServicioDTO itemDTO = new ItemServicioDTO();
        itemDTO.setPrecio(100.2);
        itemDTO.setTipoServicioId(1);
        servicioDTO.setListaItems(Set.of(itemDTO));

        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(1);

        when(clienteService.buscarPorId(1L)).thenReturn(null); 
        when(modelRepository.save(any(Servicio.class))).thenReturn(servicio);
        when(tipoServicioService.buscarPorId(1)).thenReturn(tipoServicio);

        Servicio result = servicioService.RegistrearServicio(servicioDTO);

        assertNotNull(result);
        verify(modelRepository, times(1)).save(any(Servicio.class));
        verify(itemServicioService, times(1)).guardar(any(ItemServicio.class));
    }
}
