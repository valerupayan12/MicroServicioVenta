package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.repository.ClienteRepository;
import com.example.MicroVenta.service.ClienteService;


@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockitoBean
    private ClienteRepository clienteRepository;

    //OBTENER CLIENTES
    @Test
    public void testObtenerClientes() {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Juan");
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.obtenerClientes();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    //BUSCAR CLIENTE
    @Test
    public void testBuscarCliente() {
        int id = 1;
        Cliente cliente = new Cliente();
        cliente.setId_cliente(id);
        cliente.setNombre("Juan");
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        Cliente encontrado = clienteService.buscarCliente(id);
        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());
    }

    //CREAR CLIENTE
    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente creado = clienteService.crearCliente(cliente);
        assertNotNull(creado);
        assertEquals("Juan", creado.getNombre());
    }

    //ACTUALIZAR CLIENTE
    @Test
    public void testActualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Juan Actualizado");
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente actualizado = clienteService.actualizarCliente(cliente);
        assertNotNull(actualizado);
        assertEquals("Juan Actualizado", actualizado.getNombre());
    }

    //ELIMINAR CLIENTE
    @Test
    public void testEliminarCliente() {
        int id = 1;
        when(clienteRepository.existsById(id)).thenReturn(false);
        int resultado = clienteService.eliminarCliente(id);
        assertEquals(0, resultado);
        verify(clienteRepository, never()).deleteById(id);
    }

    //ELIMINAR CLIENTE existe
    @Test
    public void testEliminarCliente_existe() {
        int id = 1;
        when(clienteRepository.existsById(id)).thenReturn(true);
        int resultado = clienteService.eliminarCliente(id);
        assertEquals(1, resultado);
        verify(clienteRepository).deleteById(id);
    }

    //BUSCAR CLIENTE no existe
    @Test
    public void testBuscarCliente_noExiste() {
        int id = 99;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());
        Cliente resultado = clienteService.buscarCliente(id);
        assertNotNull(resultado);
        assertEquals(0, resultado.getId_cliente()); 
    }
}