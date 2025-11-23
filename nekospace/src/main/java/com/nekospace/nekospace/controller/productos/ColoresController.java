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

import com.nekospace.nekospace.model.productos.Colores;
import com.nekospace.nekospace.service.productos.ColoresService;

@RestController
@RequestMapping("/api/colores")
public class ColoresController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    public ResponseEntity<List<Colores>> getAllColores() {
        List<Colores> colores = coloresService.findAll();
        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colores> getColoresById(@PathVariable Integer id) {
        Colores colores = coloresService.findById(id);
        if (colores == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colores);
    }

    @PostMapping
    public ResponseEntity<Colores> createColores(@RequestBody Colores colores) {
        Colores createdColores = coloresService.save(colores);
        return ResponseEntity.status(201).body(createdColores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colores> updateColores(@PathVariable Integer id, @RequestBody Colores colores) {
        colores.setId(id);
        Colores updatedColores = coloresService.save(colores);
        if (updatedColores == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedColores);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Colores> updatePartialColores(@PathVariable Integer id, @RequestBody Colores colores) {
        colores.setId(id);
        Colores updatedColores = coloresService.partialUpdate(colores);
        if (updatedColores == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedColores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColores(@PathVariable Integer id) {
        coloresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}