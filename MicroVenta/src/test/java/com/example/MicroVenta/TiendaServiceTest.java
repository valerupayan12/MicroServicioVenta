package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MicroVenta.model.Tienda;
import com.example.MicroVenta.repository.TiendaRepository;
import com.example.MicroVenta.service.TiendaService;

@SpringBootTest
public class TiendaServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private TiendaService tiendaService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @Mock
    private TiendaRepository tiendaRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(tiendaRepository.findAll()).thenReturn(List.of(new Tienda("1", "Ingeniería")));

        // Llama al método findAll() del servicio.
        List<Tienda> tiendas = tiendaService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(tiendas);
        assertEquals(1, tiendas.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Tienda tienda = new Tienda  (codigo, "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(tiendaRepository.findById(codigo)).thenReturn(Optional.of(tienda));

        // Llama al método findByCodigo() del servicio.
        Tienda found = tiendaService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        Tienda tienda = new Tienda ("1", "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        // Llama al método save() del servicio.
        Tienda saved = tiendaService.save(tienda);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Ingeniería", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(tiendaRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        tiendaService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(tiendaRepository, times(1)).deleteById(codigo);
    }
}
