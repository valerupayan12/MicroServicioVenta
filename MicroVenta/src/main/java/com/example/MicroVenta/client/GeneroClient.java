package com.example.MicroVenta.client;

import com.example.MicroVenta.dto.GeneroDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Cliente HTTP para consumir ms-genero usando WebClient.
 *
 * Usa el bean WebClient configurado en WebClientConfig y realiza
 * llamadas síncronas mediante block().
 */
@Component
public class GeneroClient {

    private final WebClient webClient;

    public GeneroClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Llama a GET {ms.genero.url}/api/generos/{id}
     */
    public GeneroDTO buscarPorId(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/generos/{id}").build(id))
                .retrieve()
                .bodyToMono(GeneroDTO.class)
                .block();
    }
}
