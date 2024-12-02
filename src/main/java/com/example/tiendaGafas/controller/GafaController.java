package com.example.tiendaGafas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendaGafas.model.Gafa;
import com.example.tiendaGafas.service.GafaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gafa")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GafaController {

    private final GafaService gafaService;

    // Método para obtener la lista de gafas
    @GetMapping
    public List<Gafa> getAllGafas() {
        return gafaService.getAllGafas();
    }
}
