package com.example.tiendaGafas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaGafas.model.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByCorreo(String correo);
    
    Optional<Usuario> findById(Integer idUsuario);

}
