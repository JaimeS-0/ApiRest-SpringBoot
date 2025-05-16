package com.jaime.apirest.service;


import com.jaime.apirest.Dto.AtraccionDto;
import com.jaime.apirest.Dto.AtraccionMapper;
import com.jaime.apirest.exception.AtraccionNotFoundException;
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
    private final AtraccionMapper atraccionMapper;

    public AtraccionService(AtraccionRepository atraccionRepository, AtraccionMapper atraccionMapper) {
        this.atraccionRepository = atraccionRepository;
        this.atraccionMapper = atraccionMapper;
    }

    public List<AtraccionDto> obtenerTodas() {
        return atraccionRepository.findAll().stream()
                .map(atraccionMapper::toDto)
                .collect(Collectors.toList());
    }

    public AtraccionDto obtenerPorId(Long id) {
        Atraccion atraccion = atraccionRepository.findById(id)
                .orElseThrow(() -> new AtraccionNotFoundException("No se encontro la atraccion con ID " + id));
        return atraccionMapper.toDto(atraccion);
    }

    public AtraccionDto crear(AtraccionDto dto) {
        Atraccion atraccion = atraccionMapper.toEntity(dto);
        return atraccionMapper.toDto(atraccionRepository.save(atraccion));
    }

    public AtraccionDto actualizar(Long id, AtraccionDto dto) {
        Atraccion atraccion = atraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atracción no encontrada"));
        atraccion.setNombre(dto.getNombre());
        atraccion.setTipo(dto.getTipo());
        atraccion.setPrecio(dto.getPrecio());
        atraccion.setActiva(dto.getActiva());
        atraccion.setFechaInauguracion(dto.getFechaInauguracion());
        return atraccionMapper.toDto(atraccionRepository.save(atraccion));
    }

    public void borrar(Long id) {
        if (!atraccionRepository.existsById(id)) {
            throw new AtraccionNotFoundException("Atraccion con ID " + " no existe, no se puede borrar");
        }
        atraccionRepository.deleteById(id);
    }

    public List<AtraccionDto> listarActivas() {
        return atraccionRepository.findByActivaTrue().stream()
                .map(atraccionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AtraccionDto> buscarPorPrecioMayorQue(BigDecimal precioMinimo) {
        return atraccionRepository.buscarPorPrecioMayorQue(precioMinimo).stream()
                .map(atraccionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AtraccionDto> buscarPorEjemplo(AtraccionDto ejemploDto) {
        Atraccion ejemplo = atraccionMapper.toEntity(ejemploDto);
        return atraccionRepository.findAll(Example.of(ejemplo)).stream()
                .map(atraccionMapper::toDto)
                .collect(Collectors.toList());
    }
}