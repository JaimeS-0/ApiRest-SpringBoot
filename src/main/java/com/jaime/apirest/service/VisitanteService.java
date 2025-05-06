package com.jaime.apirest.service;


import com.jaime.apirest.Dto.VisitanteDto;
import com.jaime.apirest.Dto.VisitanteMapper;
import com.jaime.apirest.model.Visitante;
import com.jaime.apirest.repository.VisitanteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitanteService {

    private final VisitanteRepository visitanteRepository;
    private final VisitanteMapper visitanteMapper;

    public VisitanteService(VisitanteRepository visitanteRepository, VisitanteMapper visitanteMapper) {
        this.visitanteRepository = visitanteRepository;
        this.visitanteMapper = visitanteMapper;
    }

    public List<VisitanteDto> obtenerTodos() {
        return visitanteRepository.findAll().stream()
                .map(visitanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public VisitanteDto obtenerPorId(Long id) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante no encontrado"));
        return visitanteMapper.toDto(visitante);
    }

    public VisitanteDto crear(VisitanteDto dto) {
        Visitante visitante = visitanteMapper.toEntity(dto);
        return visitanteMapper.toDto(visitanteRepository.save(visitante));
    }

    public VisitanteDto actualizar(Long id, VisitanteDto dto) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante no encontrado"));
        visitante.setNombre(dto.getNombre());
        visitante.setFechaNacimiento(dto.getFechaNacimiento());
        visitante.setEntradasCompradas(dto.getEntradasCompradas());
        return visitanteMapper.toDto(visitanteRepository.save(visitante));
    }

    public void borrar(Long id) {
        visitanteRepository.deleteById(id);
    }

    public List<VisitanteDto> buscarPorNombre(String nombre) {
        return visitanteRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(visitanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<VisitanteDto> buscarPorAtraccionId(Long atraccionId) {
        return visitanteRepository.buscarPorAtraccionId(atraccionId).stream()
                .map(visitanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<VisitanteDto> buscarPorEjemplo(VisitanteDto ejemploDto) {
        Visitante ejemplo = visitanteMapper.toEntity(ejemploDto);
        return visitanteRepository.findAll(Example.of(ejemplo)).stream()
                .map(visitanteMapper::toDto)
                .collect(Collectors.toList());
    }
}
