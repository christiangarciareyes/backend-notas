package com.utp.test.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utp.test.backend.domain.entity.Nota;
import com.utp.test.backend.domain.entity.Usuario;
import com.utp.test.backend.service.NotaService;
import com.utp.test.backend.service.UsuarioService;

import io.swagger.annotations.Api;

import java.util.List;

@RestController
@Api(tags = "APIs para listar usuarios y sus notas")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private NotaService notaService;

    // API para el listado de usuarios creados al iniciar la solución
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    // API para listar las notas por el ID del usuario
	@GetMapping("/{id}")
    public ResponseEntity<List<Nota>> listarNotasUsuario(@PathVariable Long id) {
        List<Nota> notas = notaService.listarNotasUsuario(id);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }
    
}
