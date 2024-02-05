package com.utp.test.backend.domain.entity.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String nombre;
    private String apellidos;
    private String username;
    private String password;
    
    public UsuarioDTO(String nombre, String apellidos, String username, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
    }
}
