package jsges.nails.Controller;
import jsges.nails.DTO.servicios.TipoServicioDTO;
import jsges.nails.domain.servicios.TipoServicio;
import jsges.nails.service.servicios.ITipoServicioService;
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
class TipoServicioControllerIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITipoServicioService modelService;

    private TipoServicio tipoServicio;
    private TipoServicioDTO tipoServicioDTO;

    @BeforeEach
    void setUp() {
        tipoServicio = new TipoServicio();
        tipoServicio.setId(1);


        tipoServicioDTO = new TipoServicioDTO();
        tipoServicioDTO.setId(1);
    }

    @Test
    void testGetAll() throws Exception {
        when(modelService.listar()).thenReturn(List.of(tipoServicio));
        
        mockMvc.perform(get("http://localhost:8080/nails/tiposServicios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(tipoServicio.getId()));
    }

    @Test
    void testGetItems() throws Exception {
        Page<TipoServicio> tipoServicioPage = new PageImpl<>(List.of(tipoServicio), PageRequest.of(0, 1), 1);
        when(modelService.findPaginated(PageRequest.of(0, 1), modelService.listar(""))).thenReturn(tipoServicioPage);
        
        mockMvc.perform(get("http://localhost:8080/nails/tiposServiciosPageQuery")
                .param("consulta", "")
                .param("page", "0")
                .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(tipoServicio.getId()));
    }

    @Test
    void testAgregar() throws Exception {
        when(modelService.newModel(Mockito.any(TipoServicioDTO.class))).thenReturn(tipoServicio);
        
        mockMvc.perform(post("http://localhost:8080/nails/tiposServicios/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Tipo de servicio de prueba DTO\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tipoServicio.getId()));
    }

    @Test
    void testEliminar() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/nails/tipoServicioEliminar/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetPorId() throws Exception {
        when(modelService.buscarPorId(1)).thenReturn(tipoServicio);
        
        mockMvc.perform(get("http://localhost:8080/nails/tiposServicios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tipoServicio.getId()));
    }

    @Test
    void testActualizar() throws Exception {
        when(modelService.actualizar(Mockito.eq(1), Mockito.any(TipoServicio.class))).thenReturn(tipoServicio);
        
        mockMvc.perform(put("http://localhost:8080/nails/tiposServicios/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Tipo de servicio actualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tipoServicio.getId()));
    }
}
