package com.nekospace.nekospace.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.usuario.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}