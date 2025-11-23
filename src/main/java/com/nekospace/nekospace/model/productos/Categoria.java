package com.nekospace.nekospace.model.productos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreCategoria",nullable = false, length = 100)
    private String nombreCategoria;

    @Column(name = "descripcionCategoria",nullable = false, length = 300)
    private String descripcionCategoria;

    @OneToMany
    @JoinColumn(name = "codigo_subCategoria", nullable = false)
    private SubCategoria subCategoria;

}
