package com.nekospace.nekospace.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.usuario.rol;

@Repository
public interface rolRepository extends JpaRepository<rol, Integer> {

}