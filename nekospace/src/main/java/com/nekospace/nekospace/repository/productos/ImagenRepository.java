package com.nekospace.nekospace.repository.productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.productos.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

}
