package com.example.MicroVenta.client;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.MicroVenta.dto.GeneroDTO;

/**
 * Configuracion del WebClient para consumir ms-genero.
 *
 * WebClient es el cliente HTTP moderno de Spring (reemplaza a RestTemplate).
 * Aqui lo configuramos con:
 *   - baseUrl: apunta a ms-genero (localhost:8081)
 *   - Basic Auth: agrega el header Authorization en cada peticion
 */
@FeignClient(
    name = "ms-genero",
    url = "${ms.genero.url}",
    configuration = FeignClientConfig.class
)
public interface GeneroClient {

    /**
     * Llama a GET http://localhost:8081/api/generos/{id}
     * Si el género no existe, Feign lanza FeignException que manejamos en el servicio.
     */
    @GetMapping("/api/generos/{id}")
    GeneroDTO buscarPorId(@PathVariable("id") Long id);

}
