package com.jaime.apirest.service;


import com.jaime.apirest.Dto.AtraccionDto;
import com.jaime.apirest.model.Atraccion;
import com.jaime.apirest.repository.AtraccionRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtraccionService {

    private final AtraccionRepository atraccionRepository;

    public AtraccionService(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    // -------- CRUD básico --------

    public List<AtraccionDto> obtenerTodas() {
        return atraccionRepository.findAll().stream()
                .map(this::convertirA_Dto)
                .collect(Collectors.toList());
    }

    public AtraccionDto obtenerPorId(Long id) {
        Atraccion atraccion = atraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atracción no encontrada"));
        return convertirA_Dto(atraccion);
    }

    public AtraccionDto crear(AtraccionDto dto) {
        Atraccion atraccion = convertirA_Entidad(dto);
        return convertirA_Dto(atraccionRepository.save(atraccion));
    }

    public AtraccionDto actualizar(Long id, AtraccionDto dto) {
        Atraccion atraccion = atraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atracción no encontrada"));
        atraccion.setNombre(dto.getNombre());
        atraccion.setTipo(dto.getTipo());
        atraccion.setPrecio(dto.getPrecio());
        atraccion.setActiva(dto.getActiva());
        atraccion.setFechaInauguracion(dto.getFechaInauguracion());
        return convertirA_Dto(atraccionRepository.save(atraccion));
    }

    public void borrar(Long id) {
        atraccionRepository.deleteById(id);
    }

    // -------- Métodos que llaman a los nombres del repositorio --------

    public List<AtraccionDto> listarActivas() {
        return atraccionRepository.findByActivaTrue().stream()
                .map(this::convertirA_Dto)
                .collect(Collectors.toList());
    }

    public List<AtraccionDto> buscarPorPrecioMayorQue(BigDecimal precioMinimo) {
        return atraccionRepository.buscarPorPrecioMayorQue(precioMinimo).stream()
                .map(this::convertirA_Dto)
                .collect(Collectors.toList());
    }

    public List<AtraccionDto> buscarPorEjemplo(Atraccion ejemplo) {
        return atraccionRepository.findAll(Example.of(ejemplo)).stream()
                .map(this::convertirA_Dto)
                .collect(Collectors.toList());
    }

    // -------- Conversión DTO <-> Entidad --------

    private AtraccionDto convertirA_Dto(Atraccion atraccion) {
        return new AtraccionDto(
                atraccion.getNombre(),
                atraccion.getTipo(),
                atraccion.getPrecio(),
                atraccion.getActiva(),
                atraccion.getFechaInauguracion(),
                null, // Aquí puedes mapear IDs de empleados si lo deseas
                null  // Aquí puedes mapear IDs de visitantes si lo deseas
        );
    }

    private Atraccion convertirA_Entidad(AtraccionDto dto) {
        Atraccion atraccion = new Atraccion();
        atraccion.setNombre(dto.getNombre());
        atraccion.setTipo(dto.getTipo());
        atraccion.setPrecio(dto.getPrecio());
        atraccion.setActiva(dto.getActiva());
        atraccion.setFechaInauguracion(dto.getFechaInauguracion());
        return atraccion;
    }
}
