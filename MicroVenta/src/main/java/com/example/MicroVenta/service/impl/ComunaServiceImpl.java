package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.dto.ComunaDTO;
import com.example.MicroVenta.model.Comuna;
import com.example.MicroVenta.service.ComunaService;
import com.example.MicroVenta.repository.ComunaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComunaServiceImpl implements ComunaService {

    private final ComunaRepository comunaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComunaDTO.Response> listarTodos() {
        log.info("[ms-envio] Listando comunas");
        return comunaRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ComunaDTO.Response buscarPorId(int id) {
        log.info("[ms-envio] Buscando comuna id: {}", id);
        Comuna c = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));
        return mapToResponse(c);
    }

    @Override
    @Transactional
    public ComunaDTO.Response crear(ComunaDTO.Request request) {
        log.info("[ms-envio] Creando comuna: {}", request.getNombre());
        Comuna c = new Comuna();
        c.setNombre(request.getNombre());
        return mapToResponse(comunaRepository.save(c));
    }

    @Override
    @Transactional
    public ComunaDTO.Response actualizar(int id, ComunaDTO.Request request) {
        log.info("[ms-envio] Actualizando comuna id: {}", id);
        Comuna c = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + id));
        c.setNombre(request.getNombre());
        return mapToResponse(comunaRepository.save(c));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        log.info("[ms-envio] Eliminando comuna id: {}", id);
        if (!comunaRepository.existsById(id))
            throw new RuntimeException("Comuna no encontrada con id: " + id);
        comunaRepository.deleteById(id);
    }

    private ComunaDTO.Response mapToResponse(Comuna c) {
        return new ComunaDTO.Response(c.getId(), c.getNombre());
    }

}
