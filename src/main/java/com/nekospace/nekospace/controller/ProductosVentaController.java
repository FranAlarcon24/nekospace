package com.nekospace.nekospace.controller;

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

import com.nekospace.nekospace.model.ProductosVenta;
import com.nekospace.nekospace.service.ProductosVentaService;

@RestController
@RequestMapping("/api/productosVentas")
public class ProductosVentaController {

    @Autowired
    private ProductosVentaService productosVentaService;
    
    @GetMapping
    public ResponseEntity<List<ProductosVenta>> getAllProductosVentas() {
        List<ProductosVenta> productosVentas = productosVentaService.findAll();
        if (productosVentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productosVentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosVenta> getProductosVentaById(@PathVariable Integer id) {
        ProductosVenta productosVenta = productosVentaService.findById(id);
        if (productosVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productosVenta);
    }

    @PostMapping
    public ResponseEntity<ProductosVenta> createProductosVenta(@RequestBody ProductosVenta productosVenta) {
        ProductosVenta createdProductosVenta = productosVentaService.save(productosVenta);
        return ResponseEntity.status(201).body(createdProductosVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosVenta> updateProductosVenta(@PathVariable Integer id, @RequestBody ProductosVenta productosVenta) {
        productosVenta.setId(id);
        ProductosVenta updatedProductosVenta = productosVentaService.save(productosVenta);
        if (updatedProductosVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProductosVenta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductosVenta> updatePartialProductosVenta(@PathVariable Integer id, @RequestBody ProductosVenta productosVenta) {
        productosVenta.setId(id);
        ProductosVenta updatedProductosVenta = productosVentaService.partialUpdate(productosVenta);
        if (updatedProductosVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProductosVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductosVenta(@PathVariable Integer id) {
        productosVentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}