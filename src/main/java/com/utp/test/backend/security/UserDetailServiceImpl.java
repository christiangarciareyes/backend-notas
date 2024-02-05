package com.utp.test.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework. stereotype.Service;

import com.utp.test.backend.domain.entity.Usuario;
import com.utp.test.backend.domain.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository
				.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario "+username+" no existe."));
		
		return new UserDetailImpl(usuario);}}
