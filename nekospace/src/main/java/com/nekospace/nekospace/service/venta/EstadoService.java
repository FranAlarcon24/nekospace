package com.nekospace.nekospace.service.venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.venta.Estado;
import com.nekospace.nekospace.repository.venta.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(Integer id) {
        Estado estado = estadoRepository.findById(id).orElse(null);
        return estado;
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

     public Estado partialUpdate(Estado estado){
        Estado existingEstado = estadoRepository.findById(estado.getId()).orElse(null);
        if (existingEstado != null) {
            if (estado.getNombreEstado() != null) {
                existingEstado.setNombreEstado(estado.getNombreEstado());
            }

            if(estado.getDescripcionEstado() != null) {
                existingEstado.setDescripcionEstado(estado.getDescripcionEstado());
            }
            return estadoRepository.save(existingEstado);
        }
        return null;
    }

    public void deleteById(Integer id) {
        estadoRepository.deleteById(id);
    }
}
