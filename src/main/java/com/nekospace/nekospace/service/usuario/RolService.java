package com.nekospace.nekospace.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.usuario.Rol;
import com.nekospace.nekospace.repository.usuario.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository RolaRepository;

    public List<Rol> findAll() {
        return RolaRepository.findAll();
    }

    @SuppressWarnings("null")
    public Rol findById(Integer id) {
        return RolaRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Rol save(Rol rol) {
        return RolaRepository.save(rol);
    }

    public Rol partialUpdate(Rol rol) {
        Rol existingRol = RolaRepository.findById(rol.getId()).orElse(null);
        if (existingRol != null) {
            if (rol.getNombreRol() != null) {
                existingRol.setNombreRol(rol.getNombreRol());
            }
            if(rol.getDescripcionRol() != null) {
                existingRol.setDescripcionRol(rol.getDescripcionRol());
            }
            return RolaRepository.save(existingRol);
        }
        return null;
    }

    public void deleteById(Integer id) {
        RolaRepository.deleteById(id);
    }
}