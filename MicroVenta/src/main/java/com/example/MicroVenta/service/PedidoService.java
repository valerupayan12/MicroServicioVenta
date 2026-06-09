package com.example.MicroVenta.service;

import java.util.List;
<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
>>>>>>> Stashed changes
import com.example.MicroVenta.model.Pedido;
import com.example.MicroVenta.repository.PedidoRepository;

<<<<<<< Updated upstream
import jakarta.transaction.Transactional;


@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    //
    public List<Pedido> getPedidos(){
        return pedidoRepository.obtenerPedidos();
    }
    //buscar
    public Pedido getPedido(int id_pedido){
        Pedido pedidos = pedidoRepository.buscarPedido(id_pedido);
        if (pedidos!=null) {
        return pedidos;
        }else
        return new Pedido();
    }
    //eliminar
    public int deletePedido(int id_pedido){
        pedidoRepository.delete(getPedido(id_pedido));
        return 1;
    }
    //guardar
    public Pedido savePedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    //modificar
    public int updatePedido(Pedido pedido){
        pedidoRepository.save(pedido);
        return 1;
    }
=======
public interface PedidoService {
    List<Pedido> listarTodos();
    Pedido buscarPorId(int id);
    Pedido crear(Pedido request);
    Pedido actualizar(int id, Pedido request);
    void eliminar(int id);
    List<Pedido> getPedidos();
    Pedido savePedido(Pedido pedido);
    int savePedido(int id_pedido);
>>>>>>> Stashed changes

}
