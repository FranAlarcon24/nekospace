package com.nekospace.nekospace.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.usuario.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

	java.util.List<Direccion> findByComunaId(Integer comunaId);

}
