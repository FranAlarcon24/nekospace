package com.nekospace.nekospace.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @JsonProperty("nombre")
    private String nombre;
    private String correo;
    private String password;
}