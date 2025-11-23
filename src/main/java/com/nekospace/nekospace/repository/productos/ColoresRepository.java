package com.nekospace.nekospace.repository.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.productos.Colores;

@Repository
public interface ColoresRepository extends JpaRepository<Colores, Integer> {

    public List<Colores> findByColorId(Integer colorId);

}

