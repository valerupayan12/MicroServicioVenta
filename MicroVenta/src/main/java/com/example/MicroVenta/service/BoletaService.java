package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.dto.BoletaDTO;


public interface BoletaService {
    List<BoletaDTO.Response> listarTodos();
    BoletaDTO.Response buscarPorId(int id);
    BoletaDTO.Response crear(BoletaDTO.Request request);
    BoletaDTO.Response actualizar(int id, BoletaDTO.Request request);
    void eliminar(int id);
}
