package com.nekospace.nekospace.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nekospace.nekospace.model.usuario.Usuario;
import com.nekospace.nekospace.repository.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CarritoService carritoService;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    public Usuario login(Usuario usuario){
        Usuario foundUsuario = usuarioRepository.findByCorreo(usuario.getCorreo());
        if(foundUsuario != null && passwordEncoder.matches(usuario.getContrasena(), foundUsuario.getContrasena())){
            return foundUsuario;
        }
        return null;
    }

    public Usuario save(Usuario usuario) {
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario){
        Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getNombreUsuario() != null) {
                existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
            }
            if (usuario.getCorreo() != null) {
                existingUsuario.setCorreo(usuario.getCorreo());
            }
            if (usuario.getPassword() != null) {
                existingUsuario.setPassword(usuario.getPassword());
            }
            if (usuario.getRol() != null) {
                existingUsuario.setRol(usuario.getRol());
            }
            if (usuario.getDireccion() != null) {
                existingUsuario.setDireccion(usuario.getDireccion());
            }
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        List<Usuario> usuarios = usuarioService.findAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario() != null && usuario.getUsuario().getId().equals(id)) {
                usuarioService.deleteById(usuario.getId());
            }
        }
        usuarioRepository.deleteById(id);
    }
}