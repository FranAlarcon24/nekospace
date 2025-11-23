package com.nekospace.nekospace.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.usuario.Direccion;
import com.nekospace.nekospace.repository.usuario.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public Direccion findById(Integer id) {
        Direccion direccion = direccionRepository.findById(id).orElse(null);
        return direccion;
    }

    public Direccion save(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    public Direccion partialUpdate(Direccion direccion){
        Direccion existingDireccion = direccionRepository.findById(direccion.getId()).orElse(null);
        if (existingDireccion != null) {
            if (direccion.getCalle() != null) {
                existingDireccion.setCalle(direccion.getCalle());
            }
            if(direccion.getNumDireccion() != null) {
                existingDireccion.setNumDireccion(direccion.getNumDireccion());
            }

            if(direccion.getDetalleDireccion() != null) {
                existingDireccion.setDetalleDireccion(direccion.getDetalleDireccion());
            }
            if(direccion.getComuna() != null) {
                existingDireccion.setComuna(direccion.getComuna());
            }
            return direccionRepository.save(existingDireccion);
        }
        return null;
    }

    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }
}