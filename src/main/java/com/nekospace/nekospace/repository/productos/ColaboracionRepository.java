package com.nekospace.nekospace.repository.productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.productos.Colaboracion;

@Repository
public interface ColaboracionRepository extends JpaRepository<Colaboracion, Integer> {

}
