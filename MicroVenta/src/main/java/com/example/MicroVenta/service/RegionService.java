package com.example.MicroVenta.service;

import java.util.List;
<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
>>>>>>> Stashed changes
import com.example.MicroVenta.model.Region;
import com.example.MicroVenta.repository.RegionRepository;

<<<<<<< Updated upstream
import jakarta.transaction.Transactional;

=======
public interface RegionService {
    List<Region> listarTodos();
    Region buscarPorId(int id);
    Region crear(Region request);
    Region actualizar(int id, Region request);
    void eliminar(int id);
    List<Region> getRegiones();
    int saveRegion1(int id_region);
    Region saveRegion(int id_region);
>>>>>>> Stashed changes

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    //obtener
    public List<Region> getRegiones(){
        return regionRepository.obtenerRegiones();
    }
    //bucar
    public Region getRegion(int id_region){
        Region regiones = regionRepository.buscarRegion(id_region);
        if (regiones!=null) {
        return regiones;
        }else
        return new Region();
    }
    //eliminar
    public int deleteRegion(int id_region){
        regionRepository.deleteById(id_region);
        return 1;
    }
    //guardar
    public Region saveRegion(Region region){
        return regionRepository.save(region);
    }
    //modificar
    public Region updateRegion(int id_region, Region region){
        Region regionExistente = getRegion(id_region);
        if (regionExistente != null && regionExistente.getId_region() != 0) {
            region.setId_region(id_region);
            return regionRepository.save(region);
        }
        return null;
    }

}
