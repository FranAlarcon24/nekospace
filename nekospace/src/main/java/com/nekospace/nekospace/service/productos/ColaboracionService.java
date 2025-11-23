package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Colaboracion;
import com.nekospace.nekospace.repository.productos.ColaboracionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ColaboracionService {

    @Autowired
    private ColaboracionRepository colaboracionRepository;

    @Autowired
    private FranquiciaService franquiciaService;

    public List<Colaboracion> findAll() {
        return colaboracionRepository.findAll();
    }

    public Colaboracion findById(Integer id) {
        Colaboracion colaboracion = colaboracionRepository.findById(id).orElse(null);
        return colaboracion;
    }

    public Colaboracion save(Colaboracion colaboracion) {
        return colaboracionRepository.save(colaboracion);
    }

    public Colaboracion partialUpdate(Colaboracion colaboracion){
        Colaboracion existingColaboracion = colaboracionRepository.findById(colaboracion.getId()).orElse(null);
        if (existingColaboracion != null) {
            if (colaboracion.getNombreColaboracion() != null) {
                existingColaboracion.setNombreColaboracion(colaboracion.getNombreColaboracion());
            }
            if(colaboracion.getDescripcionColaboracion() != null) {
                existingColaboracion.setDescripcionColaboracion(colaboracion.getDescripcionColaboracion());
            }
            return colaboracionRepository.save(existingColaboracion);
        }
        return null;
    }
    public void deleteById(Integer id) {
        franquiciaService.deleteByColaboracionId(id);
        colaboracionRepository.deleteById(id);
    }
}