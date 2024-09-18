package com.example.tiendaGafas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name = "detallePedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallePedido;
    private Integer cantidad;

    @OneToOne
    private Pedido pedido;

    @ManyToOne
    private Gafa gafa;
}
