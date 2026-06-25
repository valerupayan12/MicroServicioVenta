package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.dto.VentaDTO;
import com.example.MicroVenta.model.Venta;
import com.example.MicroVenta.repository.VentaRepository;
import com.example.MicroVenta.service.impl.VentaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    @Test
    public void testListarTodos() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        venta.setFecha_venta(Date.valueOf("2025-01-01"));
        venta.setTotal_neto(50000);

        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<VentaDTO.Response> ventas = ventaService.listarTodos();

        assertNotNull(ventas);
        assertEquals(1, ventas.size());
        assertEquals(1, ventas.get(0).getId_venta());
        assertEquals(50000, ventas.get(0).getTotal_neto());
    }

    @Test
    public void testBuscarPorId_existe() {
        int id = 1;
        Venta venta = new Venta();
        venta.setId_venta(id);
        venta.setFecha_venta(Date.valueOf("2025-01-01"));
        venta.setTotal_neto(30000);

        when(ventaRepository.findById(id)).thenReturn(Optional.of(venta));

        VentaDTO.Response found = ventaService.buscarPorId(id);

        assertNotNull(found);
        assertEquals(id, found.getId_venta());
        assertEquals(30000, found.getTotal_neto());
    }

    @Test
    public void testBuscarPorId_noExiste() {
        int id = 99;

        when(ventaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ventaService.buscarPorId(id));
    }

    @Test
    public void testCrear() {
        VentaDTO.Request request = new VentaDTO.Request(
            1, 1, 1,
            Date.valueOf("2025-01-01"),
            50000, 0, "boleta"
        );

        Venta ventaGuardada = new Venta();
        ventaGuardada.setId_venta(1);
        ventaGuardada.setFecha_venta(request.getFecha_venta());
        ventaGuardada.setTotal_neto(request.getTotal_neto());

        when(ventaRepository.save(org.mockito.ArgumentMatchers.any(Venta.class)))
            .thenReturn(ventaGuardada);

        VentaDTO.Response created = ventaService.crear(request);

        assertNotNull(created);
        assertEquals(50000, created.getTotal_neto());
    }

    @Test
    public void testActualizar_existe() {
        int id = 1;
        Venta existente = new Venta();
        existente.setId_venta(id);
        existente.setTotal_neto(20000);

        VentaDTO.Request request = new VentaDTO.Request(
            1, 1, 1,
            Date.valueOf("2025-06-01"),
            80000, 500, "factura"
        );

        Venta ventaActualizada = new Venta();
        ventaActualizada.setId_venta(id);
        ventaActualizada.setFecha_venta(request.getFecha_venta());
        ventaActualizada.setTotal_neto(request.getTotal_neto());

        when(ventaRepository.findById(id)).thenReturn(Optional.of(existente));
        when(ventaRepository.save(existente)).thenReturn(ventaActualizada);

        VentaDTO.Response updated = ventaService.actualizar(id, request);

        assertNotNull(updated);
        assertEquals(80000, updated.getTotal_neto());
    }

    @Test
    public void testActualizar_noExiste() {
        int id = 99;

        when(ventaRepository.findById(id)).thenReturn(Optional.empty());

        VentaDTO.Request request = new VentaDTO.Request(
            1, 1, 1,
            Date.valueOf("2025-06-01"),
            80000, 500, "factura"
        );

        assertThrows(RuntimeException.class, () -> ventaService.actualizar(id, request));
    }

    @Test
    public void testEliminar_existe() {
        int id = 1;

        when(ventaRepository.existsById(id)).thenReturn(true);

        ventaService.eliminar(id);

        verify(ventaRepository).deleteById(id);
    }

    @Test
    public void testEliminar_noExiste() {
        int id = 99;

        when(ventaRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> ventaService.eliminar(id));
    }
}