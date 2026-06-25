package com.example.MicroVenta;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
public class DataLoader implements CommandLineRunner {

    @Autowired private ClienteRepository clienteRepository;
    @Autowired private VentaRepository ventarepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private CuponDescuentoRepository cuponDescuentoRepository;
    @Autowired private TiendaRepository tiendaRepository;

    @Override
    public void run(String... args) throws Exception {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
        Random random = new Random();

        // 1. Generar Clientes
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente(
                i + 1,
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                random.nextInt(10) + 1,       // comuna (int)
                faker.address().fullAddress(), // direccion_envio
                random.nextInt(3) + 1          // genero (int)
            );
            clienteRepository.save(cliente);
        }

        // 2. Generar Tiendas
        for (int i = 0; i < 10; i++) {
            Tienda tienda = new Tienda(
                i + 1,
                faker.company().name(),
                faker.address().fullAddress(),
                random.nextInt(10) + 1,  // comuna (int)
                random.nextInt(5) + 1    // region (int)
            );
            tiendaRepository.save(tienda);
        }

        // 3. Generar CuponesDescuento
        for (int i = 0; i < 50; i++) {
            CuponDescuento cupon = new CuponDescuento(
                i + 1,
                random.nextInt(9000) + 1000,               // codigo (int)
                random.nextInt(50),                         // descuento_pct (int)
                random.nextInt(5000),                       // descuento_monto (int)
                Date.valueOf("2025-12-31"),                 // fecha_expiracion
                true                                        // activo
            );
            cuponDescuentoRepository.save(cupon);
        }

        // 4. Cargar listas para relaciones
        List<Cliente> clientes = clienteRepository.findAll();
        List<Tienda> tiendas = tiendaRepository.findAll();
        List<CuponDescuento> cupones = cuponDescuentoRepository.findAll();

        // 5. Generar Pedidos
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido(
                i + 1,
                clientes.get(random.nextInt(clientes.size())),
                tiendas.get(random.nextInt(tiendas.size())),
                true,
                cupones.get(random.nextInt(cupones.size())),
                new Date(faker.date().past(30, TimeUnit.DAYS).getTime())
            );
            pedidoRepository.save(pedido);
        }

        // 6. Generar Ventas
        List<Pedido> pedidos = pedidoRepository.findAll();

        for (int i = 0; i < 20; i++) {
            Venta venta = new Venta(
                i + 1,
                pedidos.get(random.nextInt(pedidos.size())),
                tiendas.get(random.nextInt(tiendas.size())),
                clientes.get(random.nextInt(clientes.size())),
                new Date(faker.date().past(30, TimeUnit.DAYS).getTime()),
                random.nextInt(100000) + 1000,  // total_neto (int)
                random.nextInt(5000),            // descuento_aplicado (int)
                faker.options().option("boleta", "factura") // tipo_documento
            );
            ventarepository.save(venta);
        }
    }
}