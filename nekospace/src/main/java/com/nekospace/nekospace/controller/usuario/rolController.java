package com.nekospace.nekospace.controller.usuario;

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

import com.nekospace.nekospace.model.usuario.rol;
import com.nekospace.nekospace.service.usuario.rolService;

@RestController
@RequestMapping("/api/roles")
public class rolController {

    @Autowired
    private rolService rolService;

    @GetMapping
    public ResponseEntity<List<rol>> getAllRoles() {
        List<rol> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<rol> getRolById(@PathVariable Integer id) {
        rol rol = rolService.findById(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<rol> createRol(@RequestBody rol rol) {
        rol createdRol = rolService.save(rol);
        return ResponseEntity.status(201).body(createdRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<rol> updateRol(@PathVariable Integer id, @RequestBody rol rol) {
        rol.setId(id);
        rol updatedRol = rolService.save(rol);
        if (updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<rol> updatePartialRol(@PathVariable Integer id, @RequestBody rol rol) {
        rol.setId(id);
        rol updatedRol = rolService.partialUpdate(rol);
        if (updatedRol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}