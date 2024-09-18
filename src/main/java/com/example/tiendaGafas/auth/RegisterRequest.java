package com.example.tiendaGafas.auth;


import com.example.tiendaGafas.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String nombre;
    String contrasena;
    String correo;
    String direccion;
    String telefono;
    Role role;
}
