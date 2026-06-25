package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.model.Pedido;
import com.example.MicroVenta.repository.PedidoRepository;
import com.example.MicroVenta.service.impl.PedidoServiceimpl;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceimpl pedidoService;

    @Test
    public void testGetPedidos() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setEstado(true);

        when(pedidoRepository.obtenerPedidos()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.getPedidos();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testGetPedido_existe() {
        int id = 1;
        Pedido pedido = new Pedido();
        pedido.setId_pedido(id);
        pedido.setEstado(true);

        when(pedidoRepository.buscarPedido(id)).thenReturn(pedido);

        Pedido encontrado = pedidoService.getPedido(id);

        assertNotNull(encontrado);
        assertTrue(encontrado.isEstado());
    }

    @Test
    public void testGetPedido_noExiste() {
        int id = 99;

        when(pedidoRepository.buscarPedido(id)).thenReturn(null);

        Pedido resultado = pedidoService.getPedido(id);

        // La implementación retorna new Pedido() cuando no encuentra
        assertNotNull(resultado);
        assertEquals(0, resultado.getId_pedido());
    }

    @Test
    public void testSavePedido() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setEstado(false);

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido creado = pedidoService.savePedido(pedido);

        assertNotNull(creado);
        assertFalse(creado.isEstado());
    }

    @Test
    public void testUpdatePedido() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setEstado(true);

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        int resultado = pedidoService.updatePedido(pedido);

        assertEquals(1, resultado);
        verify(pedidoRepository).save(pedido);
    }

    @Test
    public void testDeletePedido_noExiste() {
        int id = 99;

        when(pedidoRepository.existsById(id)).thenReturn(false);

        int resultado = pedidoService.deletePedido(id);

        assertEquals(0, resultado);
        verify(pedidoRepository, never()).deleteById(id);
    }

    @Test
    public void testDeletePedido_existe() {
        int id = 1;

        when(pedidoRepository.existsById(id)).thenReturn(true);

        int resultado = pedidoService.deletePedido(id);

        assertEquals(1, resultado);
        verify(pedidoRepository).deleteById(id);
    }
}