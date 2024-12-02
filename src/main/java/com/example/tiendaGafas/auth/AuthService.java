package com.example.tiendaGafas.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tiendaGafas.jwt.JwtService;
import com.example.tiendaGafas.model.Role;
import com.example.tiendaGafas.model.Usuario;
import com.example.tiendaGafas.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
            );
        } catch (Exception e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            throw new RuntimeException("Error en la autenticación", e);
        }
    
        // Busca al usuario por su correo
        UserDetails usuario = userRepository.findByCorreo(request.getCorreo())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Genera el token
        String token = jwtService.getToken(usuario);
    
        // Devuelve la respuesta con el token
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
            .nombre(request.getNombre())
            .correo(request.getCorreo())
            .direccion(request.getDireccion())
            .telefono(request.getTelefono())
            .role(Role.USER)
            .contrasena(passwordEncoder.encode(request.getContrasena()))
            .build();
            
        userRepository.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }

}
