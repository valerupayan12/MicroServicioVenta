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

import com.example.MicroVenta.model.Boleta;
import com.example.MicroVenta.repository.BoletaRepository;
import com.example.MicroVenta.service.impl.BoletaServiceimpl; // ← 'i' minúscula

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServiceimpl boletaService; // ← 'i' minúscula

    // Constructor: (id_cliente, nombre, email, telefono, comuna, direccion_envio, genero)

    @Test
    public void testGetBoletas() {
        Boleta boleta = new Boleta(1, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(boletaRepository.findAll()).thenReturn(List.of(boleta));

        List<Boleta> boletas = boletaService.getBoletas();

        assertNotNull(boletas);
        assertEquals(1, boletas.size());
        assertEquals("Juan", boletas.get(0).getNombre());
    }

    @Test
    public void testGetBoletaById_existe() {
        int id = 1;
        Boleta boleta = new Boleta(id, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(boletaRepository.findById(id)).thenReturn(Optional.of(boleta));

        Boleta encontrada = boletaService.getBoletaById(id);

        assertNotNull(encontrada);
        assertEquals("Juan", encontrada.getNombre());
    }

    @Test
    public void testGetBoletaById_noExiste() {
        int id = 99;

        when(boletaRepository.findById(id)).thenReturn(Optional.empty());

        // La implementación lanza RuntimeException cuando no encuentra
        assertThrows(RuntimeException.class, () -> boletaService.getBoletaById(id));
    }

    @Test
    public void testSaveBoletas() {
        Boleta boleta = new Boleta(1, "Juan", "juan@mail.com", "912345678", 1, "Av. Principal 1", 1);

        when(boletaRepository.save(boleta)).thenReturn(boleta);

        Boleta guardada = boletaService.saveBoletas(boleta);

        assertNotNull(guardada);
        assertEquals("Juan", guardada.getNombre());
    }

    @Test
    public void testDeleteBoleta_noExiste() {
        int id = 99;

        when(boletaRepository.existsById(id)).thenReturn(false);

        int resultado = boletaService.deleteBoleta(id);

        assertEquals(0, resultado);
        verify(boletaRepository, never()).deleteById(id);
    }

    @Test
    public void testDeleteBoleta_existe() {
        int id = 1;

        when(boletaRepository.existsById(id)).thenReturn(true);

        int resultado = boletaService.deleteBoleta(id);

        assertEquals(1, resultado);
        verify(boletaRepository).deleteById(id);
    }
}