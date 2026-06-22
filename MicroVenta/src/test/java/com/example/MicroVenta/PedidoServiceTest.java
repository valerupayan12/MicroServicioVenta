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

import com.example.MicroVenta.repository.PedidoRepository;
import com.example.MicroVenta.service.PedidoService;

@SpringBootTest
public class PedidoServiceTest<Pedido>  {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private PedidoService PedidoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(pedidoRepository.findAll()).thenReturn(List.of(new Pedido("1", "Ingeniería")));

        // Llama al método findAll() del servicio.
        List<Pedido> pedidos = PedidoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Pedido pedido = new Pedido(codigo, "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(pedidoRepository.findById(codigo)).thenReturn(Optional.of(pedido));

        // Llama al método findByCodigo() del servicio.
        Pedido found = PedidoService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, ((Object) found).getCodigo());
    }

    @Test
    public void testSave() {
        Pedido pedido = new Pedido("1", "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        // Llama al método save() del servicio.
        Pedido saved = PedidoService.save(pedido        );

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Ingeniería", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(pedidoRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        PedidoService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(pedidoRepository, times(1)).deleteById(codigo);
    }

}
