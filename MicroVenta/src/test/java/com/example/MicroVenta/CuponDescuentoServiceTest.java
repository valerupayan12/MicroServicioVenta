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

import com.example.MicroVenta.contoller.CuponDescuentoController;
import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.repository.CuponDescuentoRepository;
import com.example.MicroVenta.service.CuponDescuentoService;

@SpringBootTest

public class CuponDescuentoServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(new CuponDescuento("1", 10.0)));

        // Llama al método findAll() del servicio.
        List<CuponDescuentoController> cupones = cuponDescuentoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        CuponDescuento cupon = new CuponDescuento(codigo, 10.0);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(cuponDescuentoRepository.findById(codigo)).thenReturn(Optional.of(cupon));

        // Llama al método findByCodigo() del servicio.
        CuponDescuento found = cuponDescuentoService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        CuponDescuento cupon = new CuponDescuento("1", 10.0);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(cuponDescuentoRepository.save(cupon)).thenReturn(cupon);

        // Llama al método save() del servicio.
        CuponDescuento saved = cuponDescuentoService.save(cupon);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(10.0, saved.getDescuento(), 0.01);
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(cuponDescuentoRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        cuponDescuentoService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(cuponDescuentoRepository, times(1)).deleteById(codigo);
    }
}
