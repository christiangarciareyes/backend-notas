package com.utp.test.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.utp.test.backend.domain.entity.Nota;
import com.utp.test.backend.domain.entity.dto.NotaDTO;
import com.utp.test.backend.service.NotaService;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@Api(tags = "APIs para crear y listar notas")
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    // API para la creación de notas
    @PostMapping
    public ResponseEntity<Object> crearNota(@Valid @RequestBody NotaDTO notaDTO, BindingResult result) {
        if (notaDTO == null || notaDTO.getTitulo() == null || notaDTO.getContenido() == null || notaDTO.getIdUsuario() == null) {
            return new ResponseEntity<>("La solicitud debe contener datos de nota válidos.", HttpStatus.BAD_REQUEST);
        }
        if (result.hasErrors()) {
            List<String> errores = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        // Nota nuevaNota = notaService.crearNota(notaDTO);
        Nota nuevaNota = notaService.crearNotaBuilder(notaDTO);
        return new ResponseEntity<>(nuevaNota, HttpStatus.CREATED);
    }

    // API para el listado de notas
    @GetMapping
    public ResponseEntity<List<Nota>> listarNotas() {
        List<Nota> notas = notaService.listarNotas();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }
}
