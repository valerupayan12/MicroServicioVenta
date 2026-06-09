package com.example.MicroVenta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClienteDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El RUT es obligatorio")
        @Size(min = 3, max = 10, message = "El ID debe tener entre 3 y 10 caracteres")
        private int id_cliente;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El email debe contener al menos 2 palabras")
        private String email;

        @NotNull(message = "El ID del género es obligatorio")
        private Long generoId;

        public Integer getId_comuna() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getId_comuna'");
        }

        public String getTelefono() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTelefono'");
        }

        public String getDireccion_envio() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDireccion_envio'");
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_cliente;
        private String nombre;
        private String email;
        private String telefono;
        private ComunaDTO comuna;
        private String direccion_envio;
        private GeneroDTO genero; //objeto obtenido consultando microservicio genero
    }

}
