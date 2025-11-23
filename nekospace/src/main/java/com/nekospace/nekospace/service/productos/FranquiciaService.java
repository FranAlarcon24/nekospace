package com.nekospace.nekospace.service.productos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.nekospace.nekospace.model.productos.Franquicia;
import com.nekospace.nekospace.repository.productos.FranquiciaRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    @Autowired  
    private ProductoService productoService;

    public List<Franquicia> findAll() {
        return franquiciaRepository.findAll();
    }

    public Franquicia findById(Integer id) {
        Franquicia franquicia = franquiciaRepository.findById(id).orElse(null);
        return franquicia;
    }

    public Franquicia save(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public Franquicia partialUpdate(Franquicia franquicia){
        Franquicia existingFranquicia = franquiciaRepository.findById(franquicia.getId()).orElse(null);
        if (existingFranquicia != null) {
            if (franquicia.getNombreFranquicia() != null) {
                existingFranquicia.setNombreFranquicia(franquicia.getNombreFranquicia());
            }

            if(franquicia.getDescripcionFranquicia() != null) {
                existingFranquicia.setDescripcionFranquicia(franquicia.getDescripcionFranquicia());
            }

            if(franquicia.getColaboracion() != null) {
                existingFranquicia.setColaboracion(franquicia.getColaboracion());
            }

            return franquiciaRepository.save(existingFranquicia);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByFranquiciaId(id);
        franquiciaRepository.deleteById(id);
    }

    public void deleteByColaboracionId(Integer colaboracionId) {
        List<Franquicia> franquicias = franquiciaRepository .findAll();
        for (Franquicia franquicia : franquicias) {
            if (franquicia.getColaboracion() != null && franquicia.getColaboracion().getId().equals(colaboracionId)) {
                franquiciaRepository.deleteById(franquicia.getId());
            }
        }
    }

    public List<Franquicia> findByColaboracionId(Integer colaboracionId) {
        return franquiciaRepository.findByColaboracionId(colaboracionId);
    }
}