package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Material;
import com.nekospace.nekospace.repository.productos.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProductoService productoService;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material findById(Integer id) {
        Material material = materialRepository.findById(id).orElse(null);
        return material;
    }
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public Material partialUpdate(Material material){
        Material existingMaterial = materialRepository.findById(material.getId()).orElse(null);
        if (existingMaterial != null) {
            if (material.getNombreMaterial() != null) {
                existingMaterial.setNombreMaterial(material.getNombreMaterial());
            }

            if(material.getDescripcionMaterial() != null) {
                existingMaterial.setDescripcionMaterial(material.getDescripcionMaterial());
            }
            return materialRepository.save(existingMaterial);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByMaterialId(id);
        materialRepository.deleteById(id);
    }
}
