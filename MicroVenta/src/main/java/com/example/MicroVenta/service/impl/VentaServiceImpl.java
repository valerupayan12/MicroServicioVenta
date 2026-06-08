package com.example.MicroVenta.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.dto.ClienteDTO;
import com.example.MicroVenta.dto.ClienteDTO.Response;
import com.example.MicroVenta.dto.VentaDTO;
import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.model.Venta;
import com.example.MicroVenta.repository.VentaRepository;
import com.example.MicroVenta.service.VentaService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    @Override
    @Transactional(readOnly = true)
    public List listarTodos() {
        log.info("[ms-envio] Listando ventas");
        return ventaRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Response buscarPorId(int id) {
        log.info("[ms-envio] Buscando venta id: {}", id);
        Venta c = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        return mapToResponse(c);
    }

    @Override
    @Transactional
    public Response crear(VentaDTO.Request request) {
        log.info("[ms-envio] Creando venta: {}", request.getNombre());
        Venta c = new Venta();
        c.setNombre(request.getNombre());
        return mapToResponse(ventaRepository.save(c));
    }

    @Override
    @Transactional
    public Response actualizar(int id, VentaDTO.Request request) {
        log.info("[ms-envio] Actualizando venta id: {}", id);
        Venta c = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        c.setNombre(request.getNombre());
        return mapToResponse(ventaRepository.save(c));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        log.info("[ms-envio] Eliminando venta id: {}", id);
        if (!ventaRepository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        ventaRepository.deleteById(id);
    }

    private ClienteDTO.Response mapToResponse(Venta c) {
        return new ClienteDTO.Response();
    }
}
