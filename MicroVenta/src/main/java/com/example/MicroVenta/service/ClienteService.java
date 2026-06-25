package com.example.MicroVenta.service;

import java.util.List;

import com.example.MicroVenta.model.Cliente;



public interface ClienteService {

    List<Cliente> obtenerClientes();

    Cliente buscarCliente(int id_cliente);

    Cliente crearCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente cliente);

    int eliminarCliente(int id_cliente);

}
