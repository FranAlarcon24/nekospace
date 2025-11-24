package com.nekospace.nekospace.model.productos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreSubCategoria",nullable = false, length = 100)
    private String nombreSubCategoria;

    @Column(name = "descripcionSubCategoria",nullable = false, length = 300)
    private String descripcionSubCategoria;

    
}
