package com.example.tiendaGafas.service;

import org.springframework.stereotype.Service;

import com.example.tiendaGafas.model.DetallePedido;
import com.example.tiendaGafas.model.Gafa;
import com.example.tiendaGafas.model.Pedido;
import com.example.tiendaGafas.model.Usuario;
import com.example.tiendaGafas.repository.GafaRepository;
import com.example.tiendaGafas.repository.PedidoRepository;
import com.example.tiendaGafas.repository.UsuarioRepository;
import com.example.tiendaGafas.repository.DetallePedidoRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final GafaRepository gafaRepository;
	private final PedidoRepository pedidoRepository;
	private final DetallePedidoRepository detallePedidoRepository;
	private final UsuarioRepository usuarioRepository;
	
    @Transactional
    public Pedido crearPedido(List<Map<String, Object>> gafaData) {
        Pedido pedido = new Pedido();
        pedido.setNumero("P-" + System.currentTimeMillis());
        pedido.setFechaCreacion(new Date());
        
        System.out.println("Gafa Data: "+gafaData);

        // Crea la lista de detalles del pedido a partir de la información recibida
        List<DetallePedido> detalles = gafaData.stream().map(detalleData -> {
            DetallePedido detalle = new DetallePedido();
            
            // Extraer la cantidad
            int cantidad = ((Number) detalleData.get("cantidad")).intValue();
            detalle.setCantidad(cantidad);

            // Extraer el objeto Gafa
            Map<String, Object> gafaMap = (Map<String, Object>) detalleData.get("gafa");
            
            System.out.println("Contenido de gafaMap: " + gafaMap);
            Gafa gafa = new Gafa();
            
            // Asignar los atributos de la Gafa
            gafa.setIdGafa(((Number) gafaMap.get("idGafa")).intValue());
            gafa.setColor((String) gafaMap.get("color"));
            gafa.setDescripcion((String) gafaMap.get("descripcion"));
            gafa.setImagen((String) gafaMap.get("imagen"));
            gafa.setMarca((String) gafaMap.get("marca"));
            gafa.setModelo((String) gafaMap.get("modelo"));
            gafa.setPrecio(((Number) gafaMap.get("precio")).doubleValue());
           
            Integer idUsuario = ((Number) gafaMap.get("idUsuario")).intValue();
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            gafa.setUsuario(usuario);

            // Guarda la gafa si es necesario, o busca en la base de datos
            // Gafa gafa = gafaRepository.findById(gafa.getIdGafa())
            //         .orElseThrow(() -> new RuntimeException("Gafa no encontrada"));

            detalle.setGafa(gafa); // Establece la Gafa en el detalle
            detalle.setPedido(pedido); // Asocia el detalle con el pedido
            return detalle;
        }).collect(Collectors.toList());

        // Asocia los detalles al pedido
        pedido.setDetalles(detalles);
        return pedidoRepository.save(pedido); // Guarda el pedido en la base de datos
    }

}
