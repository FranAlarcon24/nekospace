package com.nekospace.nekospace.repository.productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.productos.Origen;

@Repository
public interface OrigenRepository extends JpaRepository<Origen, Integer> {

}
