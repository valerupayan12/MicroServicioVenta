package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.model.Tienda;
import com.example.MicroVenta.repository.TiendaRepository;
import com.example.MicroVenta.service.impl.TiendaServiceimpl;

@ExtendWith(MockitoExtension.class)
public class TiendaServiceTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaServiceimpl tiendaService;

    // Constructor: (int id_tienda, String nombre, String direccion, int comuna, int region)
    // generado por @AllArgsConstructor de Lombok

    @Test
    public void testListarTodos() {
        Tienda tienda = new Tienda(1, "Tienda Centro", "Av. Principal 123", 1, 1);

        when(tiendaRepository.findAll()).thenReturn(List.of(tienda));

        List<Tienda> tiendas = tiendaService.listarTodos();

        assertNotNull(tiendas);
        assertEquals(1, tiendas.size());
        assertEquals("Tienda Centro", tiendas.get(0).getNombre());
    }

    @Test
    public void testBuscarPorId_encontrado() {
        int id = 1;
        Tienda tienda = new Tienda(id, "Tienda Sur", "Calle Sur 456", 2, 1);

        when(tiendaRepository.findById(id)).thenReturn(Optional.of(tienda));

        Tienda found = tiendaService.buscarPorId(id);

        assertNotNull(found);
        assertEquals("Tienda Sur", found.getNombre());
    }

    @Test
    public void testBuscarPorId_noEncontrado() {
        int id = 99;

        when(tiendaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tiendaService.buscarPorId(id));
    }

    @Test
    public void testCrear() {
        Tienda tienda = new Tienda(1, "Tienda Norte", "Av. Norte 789", 3, 2);

        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        Tienda saved = tiendaService.crear(tienda);

        assertNotNull(saved);
        assertEquals("Tienda Norte", saved.getNombre());
    }

    @Test
    public void testActualizar() {
        int id = 1;
        Tienda existente = new Tienda(id, "Viejo nombre", "Calle Vieja 1", 1, 1);

        Tienda request = new Tienda(id, "Nuevo nombre", "Av. Nueva 123", 2, 1);

        when(tiendaRepository.findById(id)).thenReturn(Optional.of(existente));
        when(tiendaRepository.save(existente)).thenReturn(existente);

        Tienda updated = tiendaService.actualizar(id, request);

        assertNotNull(updated);
        assertEquals("Nuevo nombre", updated.getNombre());
        assertEquals("Av. Nueva 123", updated.getDireccion());
        assertEquals(2, updated.getComuna());
        assertEquals(1, updated.getRegion());
    }

    @Test
    public void testEliminar_encontrado() {
        int id = 1;

        when(tiendaRepository.existsById(id)).thenReturn(true);
        doNothing().when(tiendaRepository).deleteById(id);

        tiendaService.eliminar(id);

        verify(tiendaRepository, times(1)).existsById(id);
        verify(tiendaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testEliminar_noEncontrado() {
        int id = 99;

        when(tiendaRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> tiendaService.eliminar(id));
    }
}