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

import com.nekospace.nekospace.model.productos.Colaboracion;
import com.nekospace.nekospace.service.productos.ColaboracionService;

@RestController
@RequestMapping("/api/colaboraciones")
public class ColaboracionController {

    @Autowired
    private ColaboracionService colaboracionService;

    @GetMapping
    public ResponseEntity<List<Colaboracion>> getAllColaboraciones() {
        List<Colaboracion> colaboraciones = colaboracionService.findAll();
        if (colaboraciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colaboraciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaboracion> getColaboracionById(@PathVariable Integer id) {
        Colaboracion colaboracion = colaboracionService.findById(id);
        if (colaboracion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colaboracion);
    }

    @PostMapping
    public ResponseEntity<Colaboracion> createColaboracion(@RequestBody Colaboracion colaboracion) {
        Colaboracion createdColaboracion = colaboracionService.save(colaboracion);
        return ResponseEntity.status(201).body(createdColaboracion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaboracion> updateColaboracion(@PathVariable Integer id, @RequestBody Colaboracion colaboracion) {
        colaboracion.setId(id);
        Colaboracion updatedColaboracion = colaboracionService.save(colaboracion);
        if (updatedColaboracion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedColaboracion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Colaboracion> updatePartialColaboracion(@PathVariable Integer id, @RequestBody Colaboracion colaboracion) {
        colaboracion.setId(id);
        Colaboracion updatedColaboracion = colaboracionService.partialUpdate(colaboracion);
        if (updatedColaboracion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedColaboracion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColaboracion(@PathVariable Integer id) {
        colaboracionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}