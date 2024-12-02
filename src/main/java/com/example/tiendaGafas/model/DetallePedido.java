package com.example.tiendaGafas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "detallePedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallePedido;

    private Integer cantidad;

    @ManyToOne  // Cambia de @OneToOne a @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idGafa", nullable = false)
    private Gafa gafa;
    
    public Integer getIdDetallePedido() {
    	return this.idDetallePedido;
    }
}

