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

import com.nekospace.nekospace.model.productos.Imagen;
import com.nekospace.nekospace.service.productos.ImagenService;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<Imagen>> getAllImagenes() {
        List<Imagen> imagenes = imagenService.findAll();
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getImagenById(@PathVariable Integer id) {
        Imagen imagen = imagenService.findById(id);
        if (imagen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagen);
    }

    @PostMapping
    public ResponseEntity<Imagen> createImagen(@RequestBody Imagen imagen) {
        Imagen createdImagen = imagenService.save(imagen);
        return ResponseEntity.status(201).body(createdImagen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> updateImagen(@PathVariable Integer id, @RequestBody Imagen imagen) {
        imagen.setId(id);
        Imagen updatedImagen = imagenService.save(imagen);
        if (updatedImagen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedImagen);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Imagen> updatePartialImagen(@PathVariable Integer id, @RequestBody Imagen imagen) {
        imagen.setId(id);
        Imagen updatedImagen = imagenService.partialUpdate(imagen);
        if (updatedImagen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedImagen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Integer id) {
        imagenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}