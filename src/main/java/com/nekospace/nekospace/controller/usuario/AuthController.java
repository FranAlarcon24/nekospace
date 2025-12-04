package com.nekospace.nekospace.controller.usuario;
import com.nekospace.nekospace.config.JwtTokenProvider;
import com.nekospace.nekospace.dto.LoginRequest;
import com.nekospace.nekospace.dto.JwtAuthResponse;
import com.nekospace.nekospace.model.usuario.Usuario;
import com.nekospace.nekospace.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

        String rol = user.getRol() != null ? user.getRol().getNombreRol() : "USER";
        String token = tokenProvider.generateToken(user.getCorreo(), rol);
        return ResponseEntity.ok(new JwtAuthResponse(token, rol));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest login) {
        Usuario newUser = new Usuario();
        newUser.setNombreUsuario(login.getNombreUsuario());
        newUser.setCorreo(login.getCorreo());
        newUser.setPassword(login.getPassword());

        if (login.getRol() != null) {
            newUser.setRol(login.getRol());
        }

        Usuario savedUser = usuarioService.save(newUser);
        if (savedUser == null) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        return ResponseEntity.ok(savedUser);
    }
}