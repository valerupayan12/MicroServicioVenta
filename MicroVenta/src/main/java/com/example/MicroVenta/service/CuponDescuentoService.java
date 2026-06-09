package com.example.MicroVenta.service;

import java.util.List;
<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
>>>>>>> Stashed changes
import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.repository.CuponDescuentoRepository;

<<<<<<< Updated upstream
import jakarta.transaction.Transactional;

@Service
@Transactional

public class CuponDescuentoService {

    @Autowired
    //SE LLAMA AL REPOSITORIO DE CUPON DESCUENTO PARA REALIZAR LAS OPERACIONES DE LA BASE DE DATOS
    private CuponDescuentoRepository cuponDescuentoRepository;

    //OBTENER TODOS LOS CUPONES DE DESCUENTO
    public List<CuponDescuento> getAllCupones() {
        return cuponDescuentoRepository.findAll();
    }

    //OBTENER CUPON DESCUENTO POR ID
    public CuponDescuento getCuponDescuentoById(int id_cupon_descuento) {

        CuponDescuento cuponDescuentos =
                cuponDescuentoRepository.buscarCuponDescuento(id_cupon_descuento);

        if (cuponDescuentos != null) {
            return cuponDescuentos;
        } else {
            return new CuponDescuento();
        }
    }

    //CREAR CUPON DESCUENTO
    public CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento) {
        return cuponDescuentoRepository.save(cuponDescuento);
    }

    //ACTUALIZAR CUPON DESCUENTO
    public int updateCuponDescuento(CuponDescuento cuponDescuento) {
        cuponDescuentoRepository.save(cuponDescuento);
        return 1;
    }

    //ELIMINAR CUPON DESCUENTO
    public int deleteCuponDescuento(int id_cupon_descuento) {

        cuponDescuentoRepository.delete(
                getCuponDescuentoById(id_cupon_descuento)
        );

        return 1;
    }
=======
public interface CuponDescuentoService {
    List<CuponDescuento> listarTodos();
    CuponDescuento buscarPorId(int id);
    CuponDescuento crear(CuponDescuento request);
    CuponDescuento actualizar(int id, CuponDescuento request);
    void eliminar(int id);
    List<CuponDescuento> getAllCupones();
    CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento);
    CuponDescuento getCuponDescuentoById(int id_cupon_descuento);
    CuponDescuento saveCuponDescuento(int id_cupon_descuento);
    int deleteCuponDescuento(int id_cupon_descuento);
>>>>>>> Stashed changes

}
