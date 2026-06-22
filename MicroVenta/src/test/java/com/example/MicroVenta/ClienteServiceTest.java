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

import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.repository.ClienteRepository;
import com.example.MicroVenta.service.ClienteService;

@SpringBootTest
public class ClienteServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private ClienteService clienteService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente("1", "John Doe")));

        // Llama al método findAll() del servicio.
        List<Cliente> clientes = clienteService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Cliente cliente = new Cliente(codigo, "John Doe");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(clienteRepository.findById(codigo)).thenReturn(Optional.of(cliente));

        // Llama al método findByCodigo() del servicio.
        Cliente found = clienteService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente("1", "John Doe");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Llama al método save() del servicio.
        Cliente saved = clienteService.save(cliente);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("diana kin", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(clienteRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        clienteService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        Object cliente;
        verify(cliente  Repository, times(1)).deleteById(codigo);
    }
}