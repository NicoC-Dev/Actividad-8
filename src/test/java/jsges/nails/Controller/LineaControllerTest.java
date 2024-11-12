package jsges.nails.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.Mapper.LineaMapper;
import jsges.nails.controller.articulos.LineaController;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.service.articulos.ILineaService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LineaControllerTest {

    @Mock
    private ILineaService modelService;

    @Mock
    private LineaMapper mapper;

    @InjectMocks
    private LineaController controller;

    // Métodos de prueba adicionales

    /* 
     * Este test verifica que el método getAll en el controlador devuelve una lista de modelos Linea
     * y responde con un código de estado HTTP 200 (OK).
     */
    @Test
    public void testGetAll() {
        
        Linea model = new Linea();
        when(modelService.listar()).thenReturn(Collections.singletonList(model));

        
        ResponseEntity<List<Linea>> response = controller.getAll();

        
        assertEquals(ResponseEntity.ok(Collections.singletonList(model)), response);
    }

    /* 
     * Este test verifica que el método getItems en el controlador devuelve una página de LineaDTO 
     * cuando se pasan parámetros de consulta válidos.
     */
    @Test
    public void testGetItems_WithQuery_ReturnsPageOfLineas() {
        
        String consulta = "someQuery";
        int page = 0;
        int size = 10;
        LineaDTO dto = new LineaDTO();
        Page<LineaDTO> pageResult = new PageImpl<>(Collections.singletonList(dto));
        when(modelService.findPaginated(PageRequest.of(page, size), consulta)).thenReturn(pageResult);

        
        ResponseEntity<Page<LineaDTO>> response = controller.getItems(consulta, page, size);

        
        assertEquals(ResponseEntity.ok(pageResult), response);
    }

    /* 
     * Este test verifica que el método getPorId devuelve el DTO correcto cuando existe el ID.
     */
    @Test
    public void testGetPorId_ExistingId_ReturnsLineaDTO() {
        
        LineaDTO dto = new LineaDTO();
        when(modelService.buscarDTOPorId(1)).thenReturn(dto);

        
        ResponseEntity<LineaDTO> response = controller.getPorId(1);

        
        assertEquals(ResponseEntity.ok(dto), response);
    }

    /* 
     * Este test verifica que el método getPorId lanza una excepción cuando el ID no existe,
     * simulando un caso de recurso no encontrado.
     */
    @Test
    public void testGetPorId_NonExistingId_ThrowsException() {
        
        when(modelService.buscarDTOPorId(99)).thenReturn(null);

        
        assertThrows(NullPointerException.class, () -> controller.getPorId(99));
    }

    /* 
     * Este test verifica que el método actualizar realiza la actualización correctamente
     * y devuelve la entidad Linea actualizada.
     */
    @Test
    public void testActualizar_ValidIdAndDTO_UpdatesLinea() {
        
        LineaDTO modelRecibido = new LineaDTO();
        modelRecibido.setDenominacion("Linea actualizada");
        Linea updatedModel = new Linea();
        updatedModel.setDenominacion("Linea actualizada");

        when(modelService.actualizarLinea(1, modelRecibido)).thenReturn(updatedModel);

        
        ResponseEntity<Linea> response = controller.actualizar(1, modelRecibido);

        
        assertEquals(ResponseEntity.ok(updatedModel), response);
    }

    /*
     * Este test verifica que el método actualizar lanza una excepción cuando se intenta actualizar 
     * un modelo con un ID que no existe.
     */
    @Test
    public void testActualizar_NonExistingId_ThrowsException() {
        
        LineaDTO modelRecibido = new LineaDTO();
        when(modelService.actualizarLinea(99, modelRecibido)).thenThrow(new NullPointerException());

        
        assertThrows(NullPointerException.class, () -> controller.actualizar(99, modelRecibido));
    }

    
}
