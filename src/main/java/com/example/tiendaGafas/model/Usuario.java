package com.example.tiendaGafas.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "telefono")
    private String telefono;
       
    @Column(name = "contrasena")
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<Gafa> gafas;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }
}
