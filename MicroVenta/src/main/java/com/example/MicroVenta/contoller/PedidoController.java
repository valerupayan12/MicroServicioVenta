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

import com.example.MicroVenta.model.Pedido;
import com.example.MicroVenta.service.PedidoService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v2/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.getPedidos();
    }
    //agregar
    @PostMapping
    public int agregarPedido(@Valid @RequestBody int pedido){
        return pedidoService.savePedido(pedido);
     }
    //buscar
    @GetMapping("/{id_pedido}")
    public int buscarPedido(@PathVariable int id_pedido){
        return pedidoService.savePedido(id_pedido);
    }
    //actualizar
    @PutMapping("/{id_pedido}")
    public int actualizarPedido(@PathVariable int id_pedido, @Valid @RequestBody int pedido){
        return (int) ( pedidoService).savePedido(pedido);
    }
    //eliminar
    @DeleteMapping("/{id_pedido}")
    public String eliminarPedido(@PathVariable int id_pedido){
        if (pedidoService.savePedido(id_pedido)== 1) {
            return "Pedido eliminado correctamente";
        }
        return "Error al eliminar el pedido";
    }

}
