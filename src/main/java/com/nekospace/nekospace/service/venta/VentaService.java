package com.nekospace.nekospace.service.venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.venta.Venta;
import com.nekospace.nekospace.repository.venta.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(Integer id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        return venta;
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta partialUpdate(Venta venta){
        Venta existingVenta = ventaRepository.findById(venta.getId()).orElse(null);
        if (existingVenta != null) {
            if (venta.getTotalVenta() != null) {
                existingVenta.setTotalVenta(venta.getTotalVenta());
            }

            if(venta.getEstadoVenta() != null) {
                existingVenta.setEstadoVenta(venta.getEstadoVenta());
            }

            if(venta.getMetodoPago() != null) {
                existingVenta.setMetodoPago(venta.getMetodoPago());
            }
            if(venta.getMetodoEnvio() != null) {
                existingVenta.setMetodoEnvio(venta.getMetodoEnvio());
            }
            if(venta.getProductosVenta() != null) {
                existingVenta.setProductosVenta(venta.getProductosVenta());
            }
            return ventaRepository.save(existingVenta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }

}