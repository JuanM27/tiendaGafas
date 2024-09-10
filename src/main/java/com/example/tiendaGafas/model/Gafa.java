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
public class Gafa {
    private Integer id;
    private String marca;
    private String modelo;
    private String color;
    private double precio;
    private int cantidad;
    private String descripcion;
    private String imagen;

}
