package com.nekospace.nekospace.repository.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nekospace.nekospace.model.productos.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "SELECT * FROM categoria WHERE franquicia_id = ?1", nativeQuery = true)
    List<Categoria> findByFranquiciaId(Integer franquiciaId);

    public List<Categoria> findBySubCategoriaId(Integer subCategoriaId);

}