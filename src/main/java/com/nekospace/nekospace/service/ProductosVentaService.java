package com.nekospace.nekospace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.ProductosVenta;
import com.nekospace.nekospace.repository.ProductosVentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductosVentaService {

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<ProductosVenta> findAll() {
        return productosVentaRepository.findAll();
    }

    public ProductosVenta findById(Integer id) {
        ProductosVenta productosVenta = productosVentaRepository.findById(id).orElse(null);
        return productosVenta;
    }

    public ProductosVenta save(ProductosVenta productosVenta) {
        return productosVentaRepository.save(productosVenta);
    }

    public ProductosVenta partialUpdate(ProductosVenta productosVenta){
        ProductosVenta existingProductosVenta = productosVentaRepository.findById(productosVenta.getId()).orElse(null);
        if (existingProductosVenta != null) {
            if (productosVenta.getCantidadVentaProducto() != null) {
                existingProductosVenta.setCantidadVentaProducto(productosVenta.getCantidadVentaProducto());
            }
            return productosVentaRepository.save(existingProductosVenta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        productosVentaRepository.deleteById(id);
    }
}