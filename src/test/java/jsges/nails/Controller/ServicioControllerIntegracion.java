package jsges.nails.Controller;

import jsges.nails.DTO.servicios.ServicioDTO;
import jsges.nails.domain.servicios.Servicio;
import jsges.nails.service.servicios.IServicioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ServicioControllerIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IServicioService modelService;

    private ServicioDTO servicioDTO;
    private Servicio servicio;

    @BeforeEach
    void setUp() {
        servicioDTO = new ServicioDTO();
        servicioDTO.setId(1);
        
        
        servicio = new Servicio();
        servicio.setId(1);
       
    }

    /*
     * Verifica que el endpoint /servicios devuelva la lista de servicios en formato JSON 
     * y que el contenido tenga la estructura esperada
     */

    @Test
    void testGetAll() throws Exception {
        when(modelService.listar()).thenReturn(List.of(servicioDTO));
        
        mockMvc.perform(get("http://localhost:8080/nails/servicios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(servicioDTO.getId()));
                
    }

    /*
     * Prueba el endpoint /servicio/{id}
     *  que busca un servicio por su ID
     */

    @Test
    void testGetPorId() throws Exception {
        when(modelService.buscarPorId(1)).thenReturn(servicioDTO);
        
        mockMvc.perform(get("http://localhost:8080/nails/servicio/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(servicioDTO.getId()));
                
    }

    /*
     * Verifica la respuesta del endpoint /serviciosPageQuery que devuelve una página de servicios.
     */
    @Test
    void testGetItems() throws Exception {
        Page<ServicioDTO> serviciosPage = new PageImpl<>(List.of(servicioDTO), PageRequest.of(0, 1), 1);
        when(modelService.obtenerServiciosPaginados("", 0, 1)).thenReturn(serviciosPage);
        
        mockMvc.perform(get("http://localhost:8080/nails/serviciosPageQuery")
                .param("consulta", "")
                .param("page", "0")
                .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(servicioDTO.getId()));
                
    }
    
    /*
     * Este test asegura que el endpoint POST /servicios reciba un objeto ServicioDTO, 
     * lo guarde en el sistema y devuelva un objeto Servicio como respuesta
    */

    @Test
    void testAgregar() throws Exception {
        when(modelService.RegistrearServicio(Mockito.any(ServicioDTO.class))).thenReturn(servicio);
        
        mockMvc.perform(post("http://localhost:8080/nails/servicios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nombre\":\"Servicio de prueba\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(servicio.getId()));
    }
}