package com.nekospace.nekospace.model;

import com.nekospace.nekospace.model.venta.Venta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductosVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidadVentaProducto",nullable = false, length = 100)
    private Integer cantidadVentaProducto;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;
}
