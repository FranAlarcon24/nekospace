package com.nekospace.nekospace.repository.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nekospace.nekospace.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario WHERE Rol_id = ?1", nativeQuery = true)
    List<Usuario> findByRolId(Integer rolId);

    Usuario findByCorreo(String correo);

    public List<Usuario> findByComunaId(Integer comunaId);
}