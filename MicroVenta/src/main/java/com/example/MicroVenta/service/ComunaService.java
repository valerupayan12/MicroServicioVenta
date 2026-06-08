package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.dto.ComunaDTO;
import com.example.MicroVenta.model.Comuna;


public interface ComunaService {
    List<ComunaDTO.Response> listarTodos();
    ComunaDTO.Response buscarPorId(int id);
    ComunaDTO.Response crear(ComunaDTO.Request request);
    ComunaDTO.Response actualizar(int id, ComunaDTO.Request request);
    void eliminar(int id);
    List<Comuna> getComunas();
    Comuna saveComunas(Comuna comuna);
    Comuna getComunaById(int id_comuna);
    int updateComuna1(int id_comuna);
    int updateComuna(int id_comuna);

}
