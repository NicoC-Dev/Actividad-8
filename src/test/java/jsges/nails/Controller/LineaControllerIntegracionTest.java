package jsges.nails.Controller;


import jsges.nails.DTO.articulos.LineaDTO;

import jsges.nails.domain.articulos.Linea;
import jsges.nails.repository.articulos.LineaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional 
public class LineaControllerIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private LineaDTO testLineaDTO;

    @BeforeEach
    public void setup() {
        testLineaDTO = new LineaDTO();
        testLineaDTO.setDenominacion("Linea de Integracion");
    }

    @Test
    public void testAgregarLineaIntegration() throws Exception {
        // Convierte el DTO en JSON para enviarlo en la solicitud
        String jsonContent = objectMapper.writeValueAsString(testLineaDTO);

        // Ejecuta la solicitud y verificar el resultado
        mockMvc.perform(post("http://localhost:8080/nails/linea")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.denominacion").value("Linea de Integracion"));
    }

    @Test
    public void testObtenerLineaPorIdIntegration() throws Exception {
        // Guarda una entidad en la base de datos de prueba
        Linea savedLinea = new Linea();
        savedLinea.setDenominacion("Linea Guardada");
        savedLinea = lineaRepository.save(savedLinea);

        // Realiza una solicitud GET y verificar que los datos retornados son los esperados
        mockMvc.perform(get("http://localhost:8080/nails/linea/" + savedLinea.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.denominacion").value("Linea Guardada"));
    }

    @Test
    public void testEliminarLineaIntegration() throws Exception {
        // Guarda una entidad en la base de datos de prueba
        Linea savedLinea = new Linea();
        savedLinea.setDenominacion("Linea a Eliminar");
        savedLinea = lineaRepository.save(savedLinea);

        // Elimina la entidad
        mockMvc.perform(delete("http://localhost:8080/nails/lineaEliminar/" + savedLinea.getId()))
                .andExpect(status().isNoContent());

        // Verifica que la entidad ha sido eliminada
        mockMvc.perform(get("/linea/" + savedLinea.getId()))
                .andExpect(status().isNotFound());
    }
}
