package com.example.tiendaGafas.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "total")
    private double total;

    // Estado del pedido como un Enum
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPedido estado;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    // Relación con los detalles del pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetallePedido> detalles;

    public enum EstadoPedido {
        PENDIENTE,
        EN_PROCESO,
        ENTREGADO,
        CANCELADO
    }
    
    @JsonProperty("idUsuario")
    public Integer getIdUsuario() {
        return usuario != null ? usuario.getId() : null;
    }
}
