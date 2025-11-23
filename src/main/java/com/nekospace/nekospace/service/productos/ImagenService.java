package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Imagen;
import com.nekospace.nekospace.repository.productos.ImagenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ProductoService productoService;

    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    public Imagen findById(Integer id) {
        Imagen imagen = imagenRepository.findById(id).orElse(null);
        return imagen;
    }

    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public Imagen partialUpdate(Imagen imagen){
        Imagen existingImagen = imagenRepository.findById(imagen.getId()).orElse(null);
        if (existingImagen != null) {
            if (imagen.getNombreImagen() != null) {
                existingImagen.setNombreImagen(imagen.getNombreImagen());
            }

            if(imagen.getUrl() != null) {
                existingImagen.setUrl(imagen.getUrl());
            }
            return imagenRepository.save(existingImagen);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByImagenId(id);
        imagenRepository.deleteById(id);
    }
}