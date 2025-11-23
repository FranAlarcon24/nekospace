package com.nekospace.nekospace.controller.venta;

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

import com.nekospace.nekospace.model.venta.MetodoPago;
import com.nekospace.nekospace.service.venta.MetodoPagoService;

@RestController
@RequestMapping("/api/metodoPagos")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getAllMetodoPagos() {
        List<MetodoPago> metodoPagos = metodoPagoService.findAll();
        if (metodoPagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodoPagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Integer id) {
        MetodoPago metodoPago = metodoPagoService.findById(id);
        if (metodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoPago);
    }

    @PostMapping
    public ResponseEntity<MetodoPago> createMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago createdMetodoPago = metodoPagoService.save(metodoPago);
        return ResponseEntity.status(201).body(createdMetodoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        metodoPago.setId(id);
        MetodoPago updatedMetodoPago = metodoPagoService.save(metodoPago);
        if (updatedMetodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoPago);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPago> updatePartialMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        metodoPago.setId(id);
        MetodoPago updatedMetodoPago = metodoPagoService.partialUpdate(metodoPago);
        if (updatedMetodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable Integer id) {
        metodoPagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
