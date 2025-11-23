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

import com.nekospace.nekospace.model.productos.Franquicia;
import com.nekospace.nekospace.service.productos.FranquiciaService;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @GetMapping
    public ResponseEntity<List<Franquicia>> getAllFranquicias() {
        List<Franquicia> franquicias = franquiciaService.findAll();
        if (franquicias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(franquicias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franquicia> getFranquiciaById(@PathVariable Integer id) {
        Franquicia franquicia = franquiciaService.findById(id);
        if (franquicia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(franquicia);
    }

    @PostMapping
    public ResponseEntity<Franquicia> createFranquicia(@RequestBody Franquicia franquicia) {
        Franquicia createdFranquicia = franquiciaService.save(franquicia);
        return ResponseEntity.status(201).body(createdFranquicia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franquicia> updateFranquicia(@PathVariable Integer id, @RequestBody Franquicia franquicia) {
        franquicia.setId(id);
        Franquicia updatedFranquicia = franquiciaService.save(franquicia);
        if (updatedFranquicia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFranquicia);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Franquicia> updatePartialFranquicia(@PathVariable Integer id, @RequestBody Franquicia franquicia) {
        franquicia.setId(id);
        Franquicia updatedFranquicia = franquiciaService.partialUpdate(franquicia);
        if (updatedFranquicia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFranquicia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranquicia(@PathVariable Integer id) {
        franquiciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
