package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Categoria;
import com.nekospace.nekospace.repository.productos.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoService productoService;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return categoria;
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria partialUpdate(Categoria categoria){
        Categoria existingCategoria = categoriaRepository.findById(categoria.getId()).orElse(null);
        if (existingCategoria != null) {
            if (categoria.getNombreCategoria() != null) {
                existingCategoria.setNombreCategoria(categoria.getNombreCategoria());
            }

            if(categoria.getDescripcionCategoria() != null) {
                existingCategoria.setDescripcionCategoria(categoria.getDescripcionCategoria());
            }

            if(categoria.getSubCategoria() != null) {
                existingCategoria.setSubCategoria(categoria.getSubCategoria());
            }

            return categoriaRepository.save(existingCategoria);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByCategoriaId(id);
        categoriaRepository.deleteById(id);
    }
public void deleteBySubCategoriaId(Integer subCategoriaId) {
    List<Categoria> categorias = categoriaRepository.findBySubCategoriaId(subCategoriaId);
    for (Categoria categoria : categorias) {
        // eliminar productos que pertenezcan a esta categoria antes de borrar la categoria
        productoService.deleteByCategoriaId(categoria.getId());
        categoriaRepository.deleteById(categoria.getId());
    }
}

    public List <Categoria> findBySubCategoriaId(Integer subCategoriaId) {
        return categoriaRepository.findBySubCategoriaId(subCategoriaId);
    }

}