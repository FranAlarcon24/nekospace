package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Colores;
import com.nekospace.nekospace.repository.productos.ColoresRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ColoresService {

    @Autowired
    private ColoresRepository coloresRepository;

    @Autowired
    private ProductoService productoService;

    public List<Colores> findAll() {
        return coloresRepository.findAll();
    }

    public Colores findById(Integer id) {
        Colores colores = coloresRepository.findById(id).orElse(null);
        return colores;
    }

    public Colores save(Colores colores) {
        return coloresRepository.save(colores);
    }

    public Colores partialUpdate(Colores colores) {
        Colores existingColores = coloresRepository.findById(colores.getId()).orElse(null);
        if(existingColores != null) {
            if(colores.getNombreColores() != null) {
                existingColores.setNombreColores(colores.getNombreColores());
            }
            if(colores.getColor() != null) {
                existingColores.setColor(colores.getColor());
            }
            return coloresRepository.save(existingColores);
        }
        return null;
    }
    public void deleteById(Integer id) {
        productoService.deleteByColoresId(id);
        coloresRepository.deleteById(id);
    }

    public void deleteByColorId(Integer colorId) {
        List<Colores> colores = coloresRepository.findAll();
        for (Colores c : colores) {
            if (c.getColor() != null && c.getColor().getId().equals(colorId)) {
                coloresRepository.deleteById(c.getId());
            }
        }
    }

    public List<Colores> findByColorId(Integer colorId) {
        return coloresRepository.findByColorId(colorId);
    }

}