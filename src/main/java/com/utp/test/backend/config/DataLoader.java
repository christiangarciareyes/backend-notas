package com.utp.test.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utp.test.backend.service.UsuarioService;
import com.utp.test.backend.domain.entity.dto.UsuarioDTO;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void cargarDatosDePrueba() {
    	
        // Creará los datos de prueba y llamará al método crearUsuario del servicio de Usuarios
    	
        UsuarioDTO usuario1 = new UsuarioDTO("Christian", "Garcia", "admin", "admin");
        UsuarioDTO usuario2 = new UsuarioDTO("Abigail", "Gutierrez", "user", "user");

        usuarioService.crearUsuario(usuario1);
        usuarioService.crearUsuario(usuario2);
    }
}
