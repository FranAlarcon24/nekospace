package com.nekospace.nekospace.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.usuario.rol;
import com.nekospace.nekospace.repository.usuario.rolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class rolService {

    @Autowired
    private rolRepository rolRepository;

    public List<rol> findAll() {
        return rolRepository.findAll();
    }

    @SuppressWarnings("null")
    public rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public rol save(rol rol) {
        return rolRepository.save(rol);
    }

    public rol partialUpdate(rol rol) {
        rol existingRol = rolRepository.findById(rol.getId()).orElse(null);
        if (existingRol != null) {
            if (rol.getNombreRol() != null) {
                existingRol.setNombreRol(rol.getNombreRol());
            }
            if(rol.getDescripcionRol() != null) {
                existingRol.setDescripcionRol(rol.getDescripcionRol());
            }
            return rolRepository.save(existingRol);
        }
        return null;
    }

    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }
}