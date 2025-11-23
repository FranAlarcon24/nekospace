package com.nekospace.nekospace.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.venta.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
