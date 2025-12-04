package com.nekospace.nekospace.model.usuario;

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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreUsuario",nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "password",nullable = false,unique = true, length = 150)
    private String password;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private rol rol;

    @ManyToOne
    @JoinColumn(name = "Direccion_id", nullable = false)
    private Direccion direccion;
    
    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;
}
