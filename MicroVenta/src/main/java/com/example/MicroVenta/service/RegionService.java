package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.model.Region;

import jakarta.validation.Valid;

public interface RegionService {
    List<Region> listarTodos();
    Region buscarPorId(int id);
    Region crear(Region request);
    Region actualizar(int id, Region request);
    void eliminar(int id);
    List<Region> getRegiones();
    int saveRegion1(int id_region);
    Region saveRegion(int id_region);


}
