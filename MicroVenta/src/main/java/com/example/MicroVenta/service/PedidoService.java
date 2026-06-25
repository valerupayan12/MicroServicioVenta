package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.model.Pedido;


public interface PedidoService {

    List<Pedido> getPedidos();

    Pedido getPedido(int id_pedido);

    Pedido savePedido(Pedido pedido);

    int updatePedido(Pedido pedido);

    int deletePedido(int id_pedido);
}