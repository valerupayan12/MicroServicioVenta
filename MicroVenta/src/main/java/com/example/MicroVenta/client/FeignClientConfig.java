package com.example.MicroVenta.client;

/**
 * FeignClientConfig ya no se usa.
 * La autenticación Basic Auth hacia ms-genero se configura
 * directamente en WebClientConfig mediante el header Authorization.
 *
 * Si en el futuro se agrega OpenFeign al pom.xml, esta clase
 * puede reactivarse con BasicAuthRequestInterceptor de feign.auth.
 */
public class FeignClientConfig {
    // Vacía intencionalmente - ver WebClientConfig.java
}
