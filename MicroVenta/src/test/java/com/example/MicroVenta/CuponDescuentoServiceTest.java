package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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

import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.repository.CuponDescuentoRepository;
import com.example.MicroVenta.service.impl.CuponDescuentoServiceimpl;

@ExtendWith(MockitoExtension.class)
public class CuponDescuentoServiceTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoServiceimpl cuponDescuentoService;

    //lISTAR TODOS LOS CUPONES
    @Test
    public void testListarTodos() {
        CuponDescuento cupon = new CuponDescuento(1, 100, 10, 0, Date.valueOf("2025-12-31"), true);

        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> cupones = cuponDescuentoService.listarTodos();

        assertNotNull(cupones);
        assertEquals(1, cupones.size());
        assertEquals(100, cupones.get(0).getCodigo());
    }

    //BUSCAR CUPON POR ID (existe)
    @Test
    public void testBuscarPorId_encontrado() {
        int id = 1;
        CuponDescuento cupon = new CuponDescuento(id, 200, 15, 0, Date.valueOf("2025-12-31"), true);

        when(cuponDescuentoRepository.findById(id)).thenReturn(Optional.of(cupon));

        CuponDescuento found = cuponDescuentoService.buscarPorId(id);

        assertNotNull(found);
        assertEquals(200, found.getCodigo());
    }

    //BUSCAR CUPON POR ID (no existe)
    @Test
    public void testBuscarPorId_noEncontrado() {
        int id = 99;

        when(cuponDescuentoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cuponDescuentoService.buscarPorId(id));
    }

    //CREAR CUPON
    @Test
    public void testCrear() {
        CuponDescuento cupon = new CuponDescuento(1, 300, 20, 500, Date.valueOf("2025-12-31"), true);

        when(cuponDescuentoRepository.save(cupon)).thenReturn(cupon);

        CuponDescuento saved = cuponDescuentoService.crear(cupon);

        assertNotNull(saved);
        assertEquals(300, saved.getCodigo());
        assertEquals(20, saved.getDescuento_pct());
    }

    //ACTUALIZAR CUPON
    @Test
    public void testActualizar() {
        int id = 1;
        CuponDescuento existente = new CuponDescuento(id, 100, 10, 0, Date.valueOf("2025-12-31"), true);
        CuponDescuento request  = new CuponDescuento(id, 999, 25, 1000, Date.valueOf("2026-06-30"), false);

        when(cuponDescuentoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(cuponDescuentoRepository.save(existente)).thenReturn(existente);

        CuponDescuento updated = cuponDescuentoService.actualizar(id, request);

        assertNotNull(updated);
        assertEquals(999, updated.getCodigo());
        assertEquals(25, updated.getDescuento_pct());
        assertEquals(1000, updated.getDescuento_monto());
    }


    //ELIMINAR CUPON (existe)
    @Test
    public void testEliminar_encontrado() {
        int id = 1;

        when(cuponDescuentoRepository.existsById(id)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(id);

        cuponDescuentoService.eliminar(id);

        verify(cuponDescuentoRepository, times(1)).existsById(id);
        verify(cuponDescuentoRepository, times(1)).deleteById(id);
    }

    //ELIMINAR CUPON (no existe)
    @Test
    public void testEliminar_noEncontrado() {
        int id = 99;

        when(cuponDescuentoRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> cuponDescuentoService.eliminar(id));
    }

    //LISTAR TODOS LOS CUPONES

    @Test
    public void testGetAllCupones() {
        CuponDescuento cupon = new CuponDescuento(1, 100, 10, 0, Date.valueOf("2025-12-31"), true);

        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> cupones = cuponDescuentoService.getAllCupones();

        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    //BUSCAR CUPON POR ID (existe)

    @Test
    public void testBuscarCuponDescuento_encontrado() {
        int id = 1;

        when(cuponDescuentoRepository.existsById(id)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(id);

        int result = cuponDescuentoService.deleteCuponDescuento(id);

        assertEquals(1, result);
        verify(cuponDescuentoRepository, times(1)).deleteById(id);
    }

    //BUSCAR CUPON POR ID (no existe)
    @Test
    public void testDeleteCuponDescuento_noEncontrado() {
        int id = 99;

        when(cuponDescuentoRepository.existsById(id)).thenReturn(false);

        int result = cuponDescuentoService.deleteCuponDescuento(id);

        assertEquals(0, result);
    }
}