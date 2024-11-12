package jsges.nails.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import jsges.nails.DTO.articulos.LineaDTO;
import jsges.nails.domain.articulos.Linea;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.articulos.LineaRepository;
import jsges.nails.service.articulos.LineaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class LineaServiceTest {

    @Mock
    private LineaRepository modelRepository;

    @InjectMocks
    private LineaService lineaService;

    

    /*
     * Tests para el método 'guardar'
     * Verificamos que se lanza una excepción si el modelo es nulo
     */

    @Test
    public void testActualizarLineaConParametrosNulos() {
        
        assertThrows(RecursoNoEncontradoExcepcion.class, () -> lineaService.guardar(null));
    }

    /*
     * Tests para el método 'obtenerTodasLasLineas'
     * Verificamos que se obtienen todas las líneas
     * En este caso no pasa el test.
     */

    @Test
    public void testObtenerTodasLasLineas() {
        
        Linea model1 = new Linea();
        Linea model2 = new Linea();
        when(modelRepository.findAll()).thenReturn(java.util.Arrays.asList(model1, model2));

        
        Iterable<Linea> lineas = lineaService.listar();

        
        assertNotNull(lineas);
        assertEquals(2, ((java.util.List<Linea>) lineas).size());
    }

    /*
     * Tests para el método 'obtenerTodasLasLineas'
     * Verificamos que se lanza una excepción si el repositorio lanza una excepción
     */

    @Test
    public void testManejoExcepcionesRepositorio() {
        
        when(modelRepository.save(any(Linea.class))).thenThrow(new RuntimeException("Error en el repositorio"));

        
        assertThrows(RuntimeException.class, () -> lineaService.guardar(new Linea()));
    }

    /*
     * Tests para el método 'obtenerTodasLasLineas'
     * Verificamos que se elimine correctamente.
     */

    @Test
    public void testEstadoLineaEliminada() {
        
        Linea model = new Linea();
        model.setEstado(0); 

        
        model.asEliminado();

        
        assertEquals(1, model.getEstado()); 
    }

    /*
     * Verifica que el método listar() solo devuelva elementos que no están marcados como eliminados. 
     */
    @Test
    public void testListarNoEliminados() {
        Linea linea1 = new Linea();
        Linea linea2 = new Linea();
        when(modelRepository.buscarNoEliminados()).thenReturn(Arrays.asList(linea1, linea2));
        
        List<Linea> result = lineaService.listar();
        
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    /*
     * Verifica que el método buscarPorId() devuelva el modelo correspondiente si existe.
     */

     @Test
     public void testBuscarPorIdExiste() {
         Linea linea = new Linea();
         when(modelRepository.findById(1L)).thenReturn(Optional.of(linea));
     
         Linea result = lineaService.buscarPorId(1L);
     
         assertNotNull(result);
         assertEquals(linea, result);
     }

    /*
     * Verifica que el método buscarPorId() devuelva una excepcion si no existe.
     * 
     */
    @Test
    public void testBuscarPorIdNoExiste() {
        when(modelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoExcepcion.class, () -> lineaService.buscarPorId(1L));
    }

    /*
     * Verifica que el método guardar() devuelva el modelo guardado.
     */
    @Test
    public void testGuardarLinea() {
        Linea linea = new Linea();
        when(modelRepository.save(linea)).thenReturn(linea);
        
        Linea result = lineaService.guardar(linea);
        
        assertNotNull(result);
        assertEquals(linea, result);
    }

    /*
     * Verifica que el método guardar() lanza una excepción si el modelo es nulo.
     */

    @Test
    public void testGuardarLineaNula() {
        assertThrows(RecursoNoEncontradoExcepcion.class, () -> lineaService.guardar(null));
    }

    /*
     * Verifica que el método eliminar() llame al repositorio para eliminar el modelo.
     */

    @Test
    public void testEliminarLinea() {
        Linea linea = new Linea();
        lineaService.eliminar(linea);
        
        verify(modelRepository, times(1)).save(linea);
    }

    /*
     * Verifica el comportamiento de marcarComoEliminado() al marcar una línea como eliminada, 
     * así como el manejo adecuado de la excepción si la línea no existe.
     */

    @Test
    public void testMarcarComoEliminadoLineaExistente() {
        Linea linea = new Linea();
        when(modelRepository.findById(1L)).thenReturn(Optional.of(linea));
        
        lineaService.marcarComoEliminado(1L);
        
        assertEquals(1, linea.getEstado());
        verify(modelRepository, times(1)).save(linea);
    }

    

    @Test
    public void testMarcarComoEliminadoLineaNoExistente() {
        when(modelRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(RecursoNoEncontradoExcepcion.class, () -> lineaService.marcarComoEliminado(1L));
    }


    /*
     * Verifica el comportamiento de actualizarLinea() al actualizar una linea, 
     * asi como el manejo adecuado de la excepcion si la linea no existe.
     */
    @Test
    public void testActualizarLinea() {
        Linea linea = new Linea();
        LineaDTO lineaDTO = new LineaDTO();
        lineaDTO.setDenominacion("Nueva Denominacion");
        when(modelRepository.findById(1L)).thenReturn(Optional.of(linea));
        
        lineaService.actualizarLinea(1L, lineaDTO);
        
        assertEquals("Nueva Denominacion", linea.getDenominacion());
        verify(modelRepository, times(1)).save(linea);
    }

    @Test
    public void testActualizarLineaNoExiste() {
        LineaDTO lineaDTO = new LineaDTO();
        when(modelRepository.findById(1L)).thenReturn(Optional.empty());
        
        Linea result = lineaService.actualizarLinea(1L, lineaDTO);
        
        assertNull(result);
    }

    /*
     *   Verifica la paginación de resultados, asegurando que el método findPaginated() 
     *   retorne las líneas en formato DTO correctamente segmentadas en las páginas deseadas.    
    */

    @Test
    public void testFindPaginated() {
        LineaDTO lineaDTO1 = new LineaDTO();
        LineaDTO lineaDTO2 = new LineaDTO();
        List<LineaDTO> lineaDTOList = Arrays.asList(lineaDTO1, lineaDTO2);
        PageRequest pageable = PageRequest.of(0, 1);

        Page<LineaDTO> paginatedResult = lineaService.findPaginated(pageable, lineaDTOList);

        assertEquals(1, paginatedResult.getSize());
        assertEquals(2, paginatedResult.getTotalElements());
    }



}
