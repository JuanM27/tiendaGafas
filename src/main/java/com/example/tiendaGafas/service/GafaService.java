package com.example.tiendaGafas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaGafas.model.Gafa;
import com.example.tiendaGafas.repository.GafaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GafaService {

    private final GafaRepository gafaRepository;

    // Método para obtener todas las gafas
    public List<Gafa> getAllGafas() {
        return gafaRepository.findAll();
    }
}
