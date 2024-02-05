package com.utp.test.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.utp.test.backend.domain.entity.Nota;
import com.utp.test.backend.domain.entity.dto.NotaDTO;
import com.utp.test.backend.domain.model.NotaBuilder;
import com.utp.test.backend.domain.repository.NotaRepository;

import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    // Uso del Patrón DTO (Data Transfer Object) para facilitar la transferencia de datos entre capas.
    
    public Nota crearNota(NotaDTO notaDTO) {
        Nota nuevaNota = new Nota();
        nuevaNota.setTitulo(notaDTO.getTitulo());
        nuevaNota.setContenido(notaDTO.getContenido());
        nuevaNota.setIdUsuario(notaDTO.getIdUsuario());
        return notaRepository.save(nuevaNota);
    }
    
    // Uso del Patrón Builder para aumentar la flexibilidad y uso de parametros opcionales del objeto Nota
    
    public Nota crearNotaBuilder(NotaDTO notaDTO) {
        return notaRepository.save(new NotaBuilder()
                .conTitulo(notaDTO.getTitulo())
                .conContenido(notaDTO.getContenido())
                .conIdUsuario(notaDTO.getIdUsuario())
                .build());
    }
    
    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }
    
    public List<Nota> listarNotasUsuario(Long id) {
        return notaRepository.findByIdUsuario(id);
    }
    
}
