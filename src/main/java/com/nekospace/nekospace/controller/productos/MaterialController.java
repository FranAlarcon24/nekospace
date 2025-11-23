package com.nekospace.nekospace.controller.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekospace.nekospace.model.productos.Material;
import com.nekospace.nekospace.service.productos.MaterialService;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getAllMateriales() {
        List<Material> materiales = materialService.findAll();
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        Material material = materialService.findById(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(material);
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material createdMaterial = materialService.save(material);
        return ResponseEntity.status(201).body(createdMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer id, @RequestBody Material material) {
        material.setId(id);
        Material updatedMaterial = materialService.save(material);
        if (updatedMaterial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMaterial);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Material> updatePartialMaterial(@PathVariable Integer id, @RequestBody Material material) {
        material.setId(id);
        Material updatedMaterial = materialService.partialUpdate(material);
        if (updatedMaterial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}