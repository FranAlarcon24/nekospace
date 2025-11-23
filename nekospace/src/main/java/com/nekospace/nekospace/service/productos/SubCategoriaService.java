package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.SubCategoria;
import com.nekospace.nekospace.repository.productos.SubCategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<SubCategoria> findAll() {
        return subCategoriaRepository.findAll();
    }

    public SubCategoria findById(Integer id) {
        SubCategoria subCategoria = subCategoriaRepository.findById(id).orElse(null);
        return subCategoria;
    }

    public SubCategoria save(SubCategoria subCategoria) {
        return subCategoriaRepository.save(subCategoria);
    }

    public SubCategoria partialUpdate(SubCategoria subCategoria){
        SubCategoria existingSubCategoria = subCategoriaRepository.findById(subCategoria.getId()).orElse(null);
        if (existingSubCategoria != null) {
            if (subCategoria.getNombreSubCategoria() != null) {
                existingSubCategoria.setNombreSubCategoria(subCategoria.getNombreSubCategoria());
            }

            if(subCategoria.getDescripcionSubCategoria() != null) {
                existingSubCategoria.setDescripcionSubCategoria(subCategoria.getDescripcionSubCategoria());
            }
            return subCategoriaRepository.save(existingSubCategoria);
        }
        return null;
    }

    public void deleteById(Integer id) {
        categoriaService.deleteBySubCategoriaId(id);
        subCategoriaRepository.deleteById(id);
    }
}
