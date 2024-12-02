package com.example.tiendaGafas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tiendaGafas.model.Pedido;
import com.example.tiendaGafas.service.PedidoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	private final PedidoService pedidoService;
	
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody List<Map<String, Object>> gafaData) {
        Pedido pedido = pedidoService.crearPedido(gafaData);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
	
}
