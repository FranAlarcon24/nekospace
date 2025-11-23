package com.nekospace.nekospace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nekospace.nekospace.model.ProductosVenta;

public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    @Query(value = "SELECT * FROM producto_venta WHERE color_id = ?1", nativeQuery = true)
    List<ProductosVenta> findByColorId(Long colorId);
}
