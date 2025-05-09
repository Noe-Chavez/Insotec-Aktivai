package com.uacm.aktivai.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.aktivai.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	
	public Rol findByNombre(String nombre);

}
