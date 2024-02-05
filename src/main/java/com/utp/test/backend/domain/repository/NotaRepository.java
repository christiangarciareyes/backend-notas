package com.utp.test.backend.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.test.backend.domain.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
	
	List<Nota> findByIdUsuario(Long idUsuario);
	
}
