package com.nekospace.nekospace.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.usuario.Comuna;
import com.nekospace.nekospace.repository.usuario.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(Integer id) {
        Comuna comuna = comunaRepository.findById(id).orElse(null);
        return comuna;
    }

    public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna partialUpdate(Comuna comuna){
        Comuna existingComuna = comunaRepository.findById(comuna.getId()).orElse(null);
        if (existingComuna != null) {
            if (comuna.getNombreComuna() != null) {
                existingComuna.setNombreComuna(comuna.getNombreComuna());
            }

            if(comuna.getRegion() != null) {
                existingComuna.setRegion(comuna.getRegion());
            }
            return comunaRepository.save(existingComuna);
        }
        return null;
    }

    public void deleteById(Integer id) {
        // eliminar direcciones asociadas y limpiar referencias en usuarios
        direccionService.deleteByComunaId(id);
        usuarioService.clearComunaById(id);
        comunaRepository.deleteById(id);
    }

    public void deleteByRegionId(Integer regionId) {
        List<Comuna> comunas = comunaRepository.findByRegionId(regionId);
        for (Comuna comuna : comunas) {
            direccionService.deleteByComunaId(comuna.getId());
            usuarioService.clearComunaById(comuna.getId());
            comunaRepository.deleteById(comuna.getId());
        }
    }
}
