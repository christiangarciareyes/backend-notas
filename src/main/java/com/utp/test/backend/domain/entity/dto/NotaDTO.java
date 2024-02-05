package com.utp.test.backend.domain.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;

import lombok.Data;

@Data
public class NotaDTO {
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 10, message = "El título debe tener como máximo 10 caracteres")
    private String titulo;

    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(max = 100, message = "El contenido debe tener como máximo 1000 caracteres")
    private String contenido;

    @Digits(integer = 10, fraction = 0, message = "El identificador de usuario debe ser un número")
    private Long idUsuario;
}
