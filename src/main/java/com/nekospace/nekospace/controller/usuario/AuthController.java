package com.nekospace.nekospace.controller.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekospace.nekospace.config.JwtTokenProvider;
import com.nekospace.nekospace.dto.JwtAuthResponse;
import com.nekospace.nekospace.dto.LoginRequest;
import com.nekospace.nekospace.model.usuario.Rol;
import com.nekospace.nekospace.model.usuario.Usuario;
import com.nekospace.nekospace.service.usuario.UsuarioService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Crear usuario temporal para realizar el login
        Usuario tempUser = new Usuario();
        tempUser.setCorreo(loginRequest.getCorreo());
        tempUser.setPassword(loginRequest.getPassword());
        
        Usuario user = usuarioService.login(tempUser);
        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String Rol = user.getRol() != null ? user.getRol().getNombreRol() : "USER";
        String token = tokenProvider.generateToken(user.getCorreo(), Rol);
        return ResponseEntity.ok(new JwtAuthResponse(token, Rol));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest login) {

        if (login.getNombre() == null || login.getNombre().trim().isEmpty()) {
            return ResponseEntity.status(400).body("El nombre de usuario es obligatorio");
        }

        if (login.getCorreo() == null || login.getCorreo().trim().isEmpty()) {
            return ResponseEntity.status(400).body("El correo es obligatorio");
        }

        if (login.getPassword() == null || login.getPassword().trim().isEmpty()) {
            return ResponseEntity.status(400).body("La contraseña es obligatoria");
        }

        Usuario newUser = new Usuario();
        newUser.setNombreUsuario(login.getNombre());
        newUser.setCorreo(login.getCorreo());
        newUser.setPassword(login.getPassword());

        if (login.getRol() != null) {
            newUser.setRol(login.getRol());
        }else {
            Rol defaultRol = new Rol();
            defaultRol.setId(3);
            newUser.setRol(defaultRol);
        }


        Usuario savedUser = usuarioService.save(newUser);
        if (savedUser == null) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        return ResponseEntity.ok(savedUser);
    }
}