package com.example.tiendaGafas.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Column(name = "numero")
    private String numero;
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
    @Column(name = "fechaEntrega")
    private Date fechaEntrega;
    @Column(name = "estado")
    private double total;

    @ManyToOne
    private Usuario usuario;

}
