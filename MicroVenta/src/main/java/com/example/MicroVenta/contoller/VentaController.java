package com.example.MicroVenta.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroVenta.dto.VentaDTO;
import com.example.MicroVenta.service.VentaService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v2/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDTO.Response> listarVentas(){
        return ventaService.listarTodos();
    }

     // agregar
     @PostMapping
     public VentaDTO.Response agregarVenta(@Valid @RequestBody VentaDTO.Request request){
            return ventaService.crear(request);
     }
    // buscar
    @GetMapping("/{id_venta}")
    public VentaDTO.Response buscarVenta(@PathVariable int id_venta){
        return ventaService.buscarPorId(id_venta);
    }
    // actualizar
    @PutMapping("/{id_venta}")
    public VentaDTO.Response actualizarVenta(@PathVariable int id_venta, @Valid @RequestBody VentaDTO.Request request){
        return ventaService.actualizar(id_venta, request);
    }
    // eliminar
    @DeleteMapping("/{id_venta}")
    public String eliminarVenta(@PathVariable int id_venta){
        try {
            ventaService.eliminar(id_venta);
            return "Venta eliminada correctamente";
        } catch (RuntimeException e) {
            return "Error al eliminar la venta";
        }
     }

}
