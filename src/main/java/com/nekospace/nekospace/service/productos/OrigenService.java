package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Origen;
import com.nekospace.nekospace.repository.productos.OrigenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class OrigenService {

    @Autowired
    private OrigenRepository origenRepository;

    @Autowired
    private ProductoService productoService;

    public List<Origen> findAll() {
        return origenRepository.findAll();
    }

    public Origen findById(Integer id) {
        Origen origen = origenRepository.findById(id).orElse(null);
        return origen;
    }

    public Origen save(Origen origen) {
        return origenRepository.save(origen);
    }

    public Origen partialUpdate(Origen origen){
        Origen existingOrigen = origenRepository.findById(origen.getId()).orElse(null);
        if (existingOrigen != null) {
            if (origen.getNombreOrigen() != null) {
                existingOrigen.setNombreOrigen(origen.getNombreOrigen());
            }

            if(origen.getDescripcionOrigen() != null) {
                existingOrigen.setDescripcionOrigen(origen.getDescripcionOrigen());
            }
            return origenRepository.save(existingOrigen);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productoService.deleteByOrigenId(id);
        origenRepository.deleteById(id);
    }
}
