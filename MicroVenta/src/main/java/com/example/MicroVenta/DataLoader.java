package com.example.MicroVenta;

import java.util.Random;

import javax.management.loading.ClassLoaderRepository;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.model.Pedido;
import com.example.MicroVenta.model.Tienda;
import com.example.MicroVenta.model.Venta;
import com.example.MicroVenta.repository.ClienteRepository;
import com.example.MicroVenta.repository.CuponDescuentoRepository;
import com.example.MicroVenta.repository.PedidoRepository;
import com.example.MicroVenta.repository.TiendaRepository;
import com.example.MicroVenta.repository.VentaRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VentaRepository ventarepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;
    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar pedidos
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido();
            pedido.setId(i + 1);
            pedido.setFecha(faker.date().past(30, TimeUnit.DAYS));
            pedido.setTotal(faker.number().randomDouble(2, 10, 100));
            pedidoRepository.save(pedido);
        }
        // Generar ventas
        for (int i = 0; i < 5; i++) {
            Venta venta = new Venta();
            venta.setCodigo(faker.code().asin());
            venta.setNombre(faker.educator().course());
            ventarepository.save(venta);
        }

        List<Venta> ventas = ventarepository.findAll();

        // Generar CUPONDESCUENTO
        for (int i = 0; i < 50; i++) {
            CuponDescuento cupon = new CuponDescuento();
            cupon.setId(i + 1);
            cupon.setCodigo(faker.code().asin());
            cupon.setDescripcion(faker.lorem().sentence());
            cupon.setDescuento(faker.number().randomDouble(2, 0, 1));
            cuponDescuentoRepository.save(cupon);
        }

        // Generar CLIEWNTES
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente();
            cliente.setId(i + 1);
            cliente.setNombre(faker.name().fullName());
            cliente.setEmail(faker.internet().emailAddress());
            cliente.setTelefono(faker.phoneNumber().cellPhone());
            clienteRepository.save(cliente);
        }

        List<Cliente> clientes = clienteRepository.findAll();
        List<Tienda> tiendas = tiendaRepository.findAll();

        // Generar tiendas
        for (int i = 0; i < 20; i++) {
            Tienda tienda = new Tienda();
            tienda.setId(i + 1);
            tienda.setNombre(faker.company().name());
            tienda.setDireccion(faker.address().fullAddress());
            tienda.setTelefono(faker.phoneNumber().cellPhone());
            tiendaRepository.save(tienda);
        }
    }
}

