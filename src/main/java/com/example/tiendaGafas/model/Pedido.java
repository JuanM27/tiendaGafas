package com.example.tiendaGafas.model;

import java.util.Date;
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
public class Pedido {
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private double total;
}
