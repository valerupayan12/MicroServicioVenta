package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.dto.BoletaDTO;
import com.example.MicroVenta.model.Boleta;

import jakarta.validation.Valid;


public interface BoletaService {
    List<BoletaDTO.Response> listarTodos();
    BoletaDTO.Response buscarPorId(int id);
    BoletaDTO.Response crear(BoletaDTO.Request request);
    BoletaDTO.Response actualizar(int id, BoletaDTO.Request request);
    void eliminar(int id);
    List<Boleta> getBoletas();
    Boleta saveBoletas(Boleta boleta);
    Boleta getBoletaById(int id_boleta);
    int deleteBoleta(int id_boleta);
}
