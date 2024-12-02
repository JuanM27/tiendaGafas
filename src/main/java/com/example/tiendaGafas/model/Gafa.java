package com.example.tiendaGafas.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "gafa")
public class Gafa {
    @Id
    @Column(name = "idGafa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGafa;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "color")
    private String color;
    @Column(name = "precio")
    private double precio;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen")
    private String imagen;
    
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @JsonProperty("idUsuario")
    public Integer getIdUsuario() {
        return usuario != null ? usuario.getId() : null;
    }

}
