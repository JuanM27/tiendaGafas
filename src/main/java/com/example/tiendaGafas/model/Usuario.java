package com.example.tiendaGafas.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Usuario {
    private Integer id;
    private String nombre;
    private String nombreUsuario;
    private String correo;
    private String direccion;
    private String telefono;
    private String tipo;
    private String contrasena;
}
