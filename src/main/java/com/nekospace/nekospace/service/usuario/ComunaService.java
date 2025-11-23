package com.nekospace.nekospace.service.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.nekospace.nekospace.model.usuario.Comuna;
import com.nekospace.nekospace.repository.usuario.ComunaRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

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
        comunaRepository.deleteById(id);
    }
}
