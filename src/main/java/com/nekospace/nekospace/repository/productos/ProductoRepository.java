package com.nekospace.nekospace.repository.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nekospace.nekospace.model.productos.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByProductoId(Integer id);

    @Query("""
            SELECT p FROM Producto p
            JOIN p.marca e
            JOIN p.origen o
            WHERE e.nombre = :nombreMarca
            """)

        List<Producto> findByMarcaNombre(String nombreMarca);

    @Query("""
            SELECT p FROM producto p
            JOIN p.categoria c
            JOIN p.franquicia f
            WHERE f.nombre = : nombreFranquicia
            """)

    List<Producto> findByFranquiciaNombre(String nombreFranquicia);

    List<Producto> findByColoresId(Integer coloresId);

    List<Producto> findByMaterialId(Integer materialId);

    List<Producto> findByImagenId(Integer imagenId);

    List<Producto> findByMarcaId(Integer marcaId);

    List<Producto> findByOrigenId(Integer origenId);

    List<Producto> findByCategoriaId(Integer categoriaId);

    List<Producto> findByFranquiciaId(Integer franquiciaId);

    public Object findBydId(Integer id);

    List<Producto> findByUsuarioId(Integer usuarioId);
}
