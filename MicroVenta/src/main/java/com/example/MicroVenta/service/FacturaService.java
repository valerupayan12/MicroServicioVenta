package com.example.MicroVenta.service;

import java.util.List;
<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
>>>>>>> Stashed changes
import com.example.MicroVenta.model.Factura;
import com.example.MicroVenta.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {
    @Autowired
//LLAMAR REPOSITORIO
    private FacturaRepository facturaRepository;
//OBTENER TODOS
    public List<Factura> getFacturas(){
        return facturaRepository.obtenerCuponDescuentos();
    }

    //BUSCAR X id_factura
    public Factura getFactura(int id_factura){
        Factura facturas = facturaRepository.buscarFactura(id_factura);
        if (facturas!=null) {
        return facturas;
        }else
        return new Factura();
    }

    // ELIMINAR POR ID
    public int deleteFactura(int id_factura) {
        facturaRepository.delete(getFactura(id_factura));
        return 1;
    }

    // GUARDAR factura
    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    // MODIFICAR factura
    public int updateFactura(Factura factura) {
        facturaRepository.save(factura);
        return 1;
    }

<<<<<<< Updated upstream

=======
public interface FacturaService {
    List<Factura> listarTodos();
    Factura buscarPorId(int id);
    Factura crear(Factura request);
    Factura actualizar(int id, Factura request);
    void eliminar(int id);
    List<Factura> getFacturas();
    int saveFactura(Factura factura);
    Factura getFactura(int id_factura);
    int deleteFactura(int id_factura);
>>>>>>> Stashed changes
}
