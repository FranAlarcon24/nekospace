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
import com.nekospace.nekospace.repository.usuario.RolRepository;
import com.nekospace.nekospace.service.usuario.UsuarioService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Validar que correo y password no sean nulos
            if (loginRequest.getCorreo() == null || loginRequest.getCorreo().trim().isEmpty()) {
                return ResponseEntity.status(400).body("El correo es obligatorio");
            }
            if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
                return ResponseEntity.status(400).body("La contraseña es obligatoria");
            }

            Usuario tempUser = new Usuario();
            tempUser.setNombreUsuario(loginRequest.getNombre());
            tempUser.setCorreo(loginRequest.getCorreo());
            tempUser.setPassword(loginRequest.getPassword());
            
            Usuario user = usuarioService.login(tempUser);
            if (user == null) {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }

            String rolNombre = user.getRol() != null ? user.getRol().getNombreRol() : "USER";
            String token = tokenProvider.generateToken(user.getCorreo(), rolNombre);
            return ResponseEntity.ok(new JwtAuthResponse(token, rolNombre));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error en login: " + e.getMessage());
        }
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

        
        if (usuarioService.findByCorreo(login.getCorreo()) != null) {
            return ResponseEntity.status(400).body("El correo ya está registrado");
        }

        Rol rolToAssign = null;
        if (login.getRol() != null && login.getRol().getId() != null) {
            rolToAssign = rolRepository.findById(login.getRol().getId()).orElse(null);
            if (rolToAssign == null) {
                return ResponseEntity.status(400).body("Rol no encontrado");
            }
        } else {
            rolToAssign = rolRepository.findById(3).orElse(null);
            if (rolToAssign == null) {
                return ResponseEntity.status(400).body("Rol por defecto no configurado en la base de datos");
            }
        }
        newUser.setRol(rolToAssign);


        try {
            Usuario savedUser = usuarioService.save(newUser);
            if (savedUser == null) {
                return ResponseEntity.status(400).body("Error al registrar el usuario");
            }
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al registrar: " + e.getMessage());
        }
    }
}