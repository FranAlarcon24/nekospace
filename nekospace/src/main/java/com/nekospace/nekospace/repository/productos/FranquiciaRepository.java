package com.nekospace.nekospace.repository.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekospace.nekospace.model.productos.Franquicia;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Integer> {
    List<Franquicia> findByNombre(String nombre);

    @Override
    public void deleteById(Integer id);

    List<Franquicia> findByColaboracionId(Integer colaboracionId);
}
