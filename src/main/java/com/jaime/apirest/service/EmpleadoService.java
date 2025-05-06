package com.jaime.apirest.service;

import com.jaime.apirest.Dto.EmpleadoDto;
import com.jaime.apirest.model.Empleado;
import com.jaime.apirest.repository.EmpleadoRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // -------- CRUD básico --------

    public List<EmpleadoDto> obtenerTodos() {
        return empleadoRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public EmpleadoDto obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return convertirADto(empleado);
    }

    public EmpleadoDto crear(EmpleadoDto dto) {
        Empleado empleado = convertirAEntidad(dto);
        return convertirADto(empleadoRepository.save(empleado));
    }

    public EmpleadoDto actualizar(Long id, EmpleadoDto dto) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleado.setNombre(dto.getNombre());
        empleado.setPuesto(dto.getPuesto());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return convertirADto(empleadoRepository.save(empleado));
    }

    public void borrar(Long id) {
        empleadoRepository.deleteById(id);
    }

    // -------- Métodos que llaman a los nombres del repositorio --------

    public List<EmpleadoDto> buscarPorPuesto(String puesto) {
        return empleadoRepository.findByPuesto(puesto).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> buscarPorAtraccionId(Long atraccionId) {
        return empleadoRepository.buscarPorAtraccionId(atraccionId).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> buscarPorEjemplo(EmpleadoDto ejemploDto) {
        Empleado ejemplo = convertirAEntidad(ejemploDto);
        return empleadoRepository.findAll(Example.of(ejemplo)).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // -------- Conversión DTO <-> Entidad --------

    private EmpleadoDto convertirADto(Empleado empleado) {
        return new EmpleadoDto(
                empleado.getNombre(),
                empleado.getPuesto(),
                empleado.getFechaContratacion(),
                empleado.getAtraccion() != null ? empleado.getAtraccion().getId() : null
        );
    }

    private Empleado convertirAEntidad(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setPuesto(dto.getPuesto());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return empleado;
    }
}
