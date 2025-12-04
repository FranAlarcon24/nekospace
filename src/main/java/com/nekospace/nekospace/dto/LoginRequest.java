package com.nekospace.nekospace.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nekospace.nekospace.model.usuario.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @JsonProperty("nombre")
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;
}