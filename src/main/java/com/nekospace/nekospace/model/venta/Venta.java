package com.nekospace.nekospace.model.venta;

import com.nekospace.nekospace.model.ProductosVenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "totalVenta",nullable = false)
    private Double totalVenta;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estadoVenta;

    @ManyToOne
    @JoinColumn(name = "metodoPago_id", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "metodoEnvio_id", nullable = false)
    private MetodoEnvio metodoEnvio;

    @OneToMany
    @JoinColumn(name = "ProductosVenta_id", nullable = false)
    private ProductosVenta productosVenta;

}
