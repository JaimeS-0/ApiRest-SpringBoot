package com.jaime.apirest.service;


import com.jaime.apirest.Dto.VisitanteDto;
import com.jaime.apirest.model.Visitante;
import com.jaime.apirest.repository.VisitanteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitanteService {

    private final VisitanteRepository visitanteRepository;

    public VisitanteService(VisitanteRepository visitanteRepository) {
        this.visitanteRepository = visitanteRepository;
    }

    // -------- CRUD básico --------

    public List<VisitanteDto> obtenerTodos() {
        return visitanteRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public VisitanteDto obtenerPorId(Long id) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante no encontrado"));
        return convertirADto(visitante);
    }

    public VisitanteDto crear(VisitanteDto dto) {
        Visitante visitante = convertirAEntidad(dto);
        return convertirADto(visitanteRepository.save(visitante));
    }

    public VisitanteDto actualizar(Long id, VisitanteDto dto) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante no encontrado"));
        visitante.setNombre(dto.getNombre());
        visitante.setFechaNacimiento(dto.getFechaNacimiento());
        visitante.setEntradasCompradas(dto.getEntradasCompradas());
        return convertirADto(visitanteRepository.save(visitante));
    }

    public void borrar(Long id) {
        visitanteRepository.deleteById(id);
    }

    // -------- Métodos que llaman a los nombres del repositorio --------

    public List<VisitanteDto> buscarPorNombre(String nombre) {
        return visitanteRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public List<VisitanteDto> buscarPorAtraccionId(Long atraccionId) {
        return visitanteRepository.buscarPorAtraccionId(atraccionId).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public List<VisitanteDto> buscarPorEjemplo(VisitanteDto ejemploDto) {
        Visitante ejemplo = convertirAEntidad(ejemploDto);
        return visitanteRepository.findAll(Example.of(ejemplo)).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // -------- Métodos de conversión --------

    private VisitanteDto convertirADto(Visitante visitante) {
        return new VisitanteDto(
                visitante.getNombre(),
                visitante.getFechaNacimiento(),
                visitante.getEntradasCompradas(),
                null
        );
    }

    private Visitante convertirAEntidad(VisitanteDto dto) {
        Visitante visitante = new Visitante();
        visitante.setNombre(dto.getNombre());
        visitante.setFechaNacimiento(dto.getFechaNacimiento());
        visitante.setEntradasCompradas(dto.getEntradasCompradas());
        return visitante;
    }
}
