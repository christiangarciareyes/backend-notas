package com.utp.test.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.test.backend.domain.entity.Usuario;
import com.utp.test.backend.domain.entity.dto.UsuarioDTO;
import com.utp.test.backend.domain.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Uso del Patrón DTO (Data Transfer Object) para facilitar la transferencia de datos entre capas.
    
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
    	Usuario nuevoUsuario = new Usuario();
    	nuevoUsuario.setNombre(usuarioDTO.getNombre());
    	nuevoUsuario.setApellidos(usuarioDTO.getApellidos());
    	nuevoUsuario.setUsername(usuarioDTO.getUsername());
    	nuevoUsuario.setPassword(PasswordEncrypted(usuarioDTO.getPassword()));
        return usuarioRepository.save(nuevoUsuario);
    }
    
	String PasswordEncrypted (String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
}
