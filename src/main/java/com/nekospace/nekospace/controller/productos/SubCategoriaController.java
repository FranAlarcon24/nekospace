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

import com.nekospace.nekospace.model.productos.SubCategoria;
import com.nekospace.nekospace.service.productos.SubCategoriaService;

@RestController
@RequestMapping("/api/subCategorias")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subCategoriaService;

    @GetMapping
    public ResponseEntity<List<SubCategoria>> getAllSubCategorias() {
        List<SubCategoria> subCategorias = subCategoriaService.findAll();
        if (subCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subCategorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoria> getSubCategoriaById(@PathVariable Integer id) {
        SubCategoria subCategoria = subCategoriaService.findById(id);
        if (subCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subCategoria);
    }

    @PostMapping
    public ResponseEntity<SubCategoria> createSubCategoria(@RequestBody SubCategoria subCategoria) {
        SubCategoria createdSubCategoria = subCategoriaService.save(subCategoria);
        return ResponseEntity.status(201).body(createdSubCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoria> updateSubCategoria(@PathVariable Integer id, @RequestBody SubCategoria subCategoria) {
        subCategoria.setId(id);
        SubCategoria updatedSubCategoria = subCategoriaService.save(subCategoria);
        if (updatedSubCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSubCategoria);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubCategoria> updatePartialSubCategoria(@PathVariable Integer id, @RequestBody SubCategoria subCategoria) {
        subCategoria.setId(id);
        SubCategoria updatedSubCategoria = subCategoriaService.partialUpdate(subCategoria);
        if (updatedSubCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSubCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoria(@PathVariable Integer id) {
        subCategoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
