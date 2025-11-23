package com.nekospace.nekospace.repository.venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nekospace.nekospace.model.venta.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query(value = "SELECT * FROM venta WHERE usuario_id = ?1", nativeQuery = true)
    List<Venta> findByUsuarioId(Long usuarioId);
}
