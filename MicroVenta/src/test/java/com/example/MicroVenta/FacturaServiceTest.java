package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.model.Factura;
import com.example.MicroVenta.repository.FacturaRepository;
import com.example.MicroVenta.service.impl.FacturaServiceimpl;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceimpl facturaService;

    @Test
    public void testGetFacturas() {
        Factura factura = new Factura();
        factura.setId_factura(1);

        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> facturas = facturaService.getFacturas();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
    }

    @Test
    public void testGetFactura_existe() {
        int id = 1;
        Factura factura = new Factura();
        factura.setId_factura(id);
        factura.setMonto_total(100); // ← int, no double

        when(facturaRepository.findById(id)).thenReturn(Optional.of(factura));

        Factura encontrada = facturaService.getFactura(id);

        assertNotNull(encontrada);
        assertEquals(100, encontrada.getMonto_total());
    }

    @Test
    public void testGetFactura_noExiste() {
        int id = 99;

        when(facturaRepository.findById(id)).thenReturn(Optional.empty());

        // La implementación lanza RuntimeException cuando no encuentra
        assertThrows(RuntimeException.class, () -> facturaService.getFactura(id));
    }

    @Test
    public void testSaveFactura() {
        Factura factura = new Factura();
        factura.setId_factura(1);
        factura.setMonto_total(100); // ← int, no double

        when(facturaRepository.save(factura)).thenReturn(factura);

        // saveFactura retorna int (el id guardado)
        int idGuardado = facturaService.saveFactura(factura);

        assertEquals(1, idGuardado);
    }

    @Test
    public void testListarTodos() {
        Factura factura = new Factura();
        factura.setId_factura(1);

        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> facturas = facturaService.listarTodos();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
    }

    @Test
    public void testEliminar_existe() {
        int id = 1;

        when(facturaRepository.existsById(id)).thenReturn(true);

        facturaService.eliminar(id);

        verify(facturaRepository).deleteById(id);
    }

    @Test
    public void testEliminar_noExiste() {
        int id = 99;

        when(facturaRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> facturaService.eliminar(id));
    }

    @Test
    public void testDeleteFactura_noExiste() {
        int id = 99;

        when(facturaRepository.existsById(id)).thenReturn(false);

        int resultado = facturaService.deleteFactura(id);

        assertEquals(0, resultado);
        verify(facturaRepository, never()).deleteById(id);
    }

    @Test
    public void testDeleteFactura_existe() {
        int id = 1;

        when(facturaRepository.existsById(id)).thenReturn(true);

        int resultado = facturaService.deleteFactura(id);

        assertEquals(1, resultado);
        verify(facturaRepository).deleteById(id);
    }
}