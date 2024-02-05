package com.utp.test.backend.domain.model;

import com.utp.test.backend.domain.entity.Nota;

public class NotaBuilder {
    private String titulo;
    private String contenido;
    private Long idUsuario;

    public NotaBuilder conTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public NotaBuilder conContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }
    
    public NotaBuilder conIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public Nota build() {
        Nota nota = new Nota();
        nota.setTitulo(this.titulo);
        nota.setContenido(this.contenido);
        nota.setIdUsuario(this.idUsuario);
        return nota;
    }
}
