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

import com.nekospace.nekospace.model.productos.Origen;
import com.nekospace.nekospace.service.productos.OrigenService;

@RestController
@RequestMapping("/api/origenes")
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @GetMapping
    public ResponseEntity<List<Origen>> getAllOrigenes() {
        List<Origen> origenes = origenService.findAll();
        if (origenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(origenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Origen> getOrigenById(@PathVariable Integer id) {
        Origen origen = origenService.findById(id);
        if (origen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(origen);
    }

    @PostMapping
    public ResponseEntity<Origen> createOrigen(@RequestBody Origen origen) {
        Origen createdOrigen = origenService.save(origen);
        return ResponseEntity.status(201).body(createdOrigen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Origen> updateOrigen(@PathVariable Integer id, @RequestBody Origen origen) {
        origen.setId(id);
        Origen updatedOrigen = origenService.save(origen);
        if (updatedOrigen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedOrigen);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Origen> updatePartialOrigen(@PathVariable Integer id, @RequestBody Origen origen) {
        origen.setId(id);
        Origen updatedOrigen = origenService.partialUpdate(origen);
        if (updatedOrigen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedOrigen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrigen(@PathVariable Integer id) {
        origenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
