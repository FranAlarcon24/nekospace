package com.nekospace.nekospace.controller.usuario;
import com.nekospace.nekospace.config.JwtTokenProvider;
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
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario user = UsuarioService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = tokenProvider.generateToken(user.getEmail(), user.getRol().name());
        return ResponseEntity.ok(new JwtAuthResponse(token, user.getRol().name()));
    }
}