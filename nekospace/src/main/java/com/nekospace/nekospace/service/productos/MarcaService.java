package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Marca;
import com.nekospace.nekospace.repository.productos.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProductoService productoService;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Integer id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return marca;
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca partialUpdate(Marca marca){
        Marca existingMarca = marcaRepository.findById(marca.getId()).orElse(null);
        if (existingMarca != null) {
            if (marca.getNombreMarca() != null) {
                existingMarca.setNombreMarca(marca.getNombreMarca());
            }

            if(marca.getDescripcionMarca() != null) {
                existingMarca.setDescripcionMarca(marca.getDescripcionMarca());
            }
            return marcaRepository.save(existingMarca);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByMarcaId(id);
        marcaRepository.deleteById(id);
    }
}