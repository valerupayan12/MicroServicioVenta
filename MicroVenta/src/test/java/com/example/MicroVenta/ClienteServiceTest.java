package com.example.MicroVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.repository.ClienteRepository;
import com.example.MicroVenta.service.impl.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    public void testObtenerClientes() {
        Cliente cliente = new Cliente(1, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.obtenerClientes();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("Juan", clientes.get(0).getNombre());
    }

    @Test
    public void testBuscarCliente_existe() {
        int id = 1;
        Cliente cliente = new Cliente(id, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.buscarCliente(id);

        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());
    }

    @Test
    public void testBuscarCliente_noExiste() {
        int id = 99;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.buscarCliente(id);

        // La implementación retorna new Cliente() cuando no encuentra
        assertNotNull(resultado);
        assertEquals(0, resultado.getId_cliente());
    }

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente(1, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente creado = clienteService.crearCliente(cliente);

        assertNotNull(creado);
        assertEquals("Juan", creado.getNombre());
    }

    @Test
    public void testActualizarCliente() {
        Cliente cliente = new Cliente(1, "Juan Actualizado", "juan@mail.com", "912345678", 2, "Av. Nueva 123", 1);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente actualizado = clienteService.actualizarCliente(cliente);

        assertNotNull(actualizado);
        assertEquals("Juan Actualizado", actualizado.getNombre());
    }

    @Test
    public void testEliminarCliente_noExiste() {
        int id = 99;

        when(clienteRepository.existsById(id)).thenReturn(false);

        int resultado = clienteService.eliminarCliente(id);

        assertEquals(0, resultado);
        verify(clienteRepository, never()).deleteById(id);
    }

    @Test
    public void testEliminarCliente_existe() {
        int id = 1;

        when(clienteRepository.existsById(id)).thenReturn(true);

        int resultado = clienteService.eliminarCliente(id);

        assertEquals(1, resultado);
        verify(clienteRepository).deleteById(id);
    }
}