package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.productos.Color;
import com.nekospace.nekospace.repository.productos.ColorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ColorService {
    
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColoresService coloresService; //Main

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public Color findById(Integer id) {
        Color color = colorRepository.findById(id).orElse(null);
        return color;
    }

    public Color save(Color color) {
        return colorRepository.save(color);
    }

    public Color partialUpdate(Color color) {
        Color existingColor = colorRepository.findById(color.getId()).orElse(null);
        if(existingColor != null) {
            if(color.getNombreColor() != null) {
                existingColor.setNombreColor(color.getNombreColor());
            }
            if(color.getCodigoColor() != null) {
                existingColor.setCodigoColor(color.getCodigoColor());
            }
            return colorRepository.save(existingColor);
        }
        return null;
    }

    public void deleteById(Integer id) {
        coloresService.deleteByColorId(id);
        colorRepository.deleteById(id);
    }
}
