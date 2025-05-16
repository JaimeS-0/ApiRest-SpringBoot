package com.jaime.apirest.service;

import com.jaime.apirest.Dto.EmpleadoDto;
import com.jaime.apirest.Dto.EmpleadoMapper;
import com.jaime.apirest.exception.EmpleadoNotFoundException;
import com.jaime.apirest.model.Empleado;
import com.jaime.apirest.repository.EmpleadoRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    public List<EmpleadoDto> obtenerTodos() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmpleadoDto obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException("No se encontro el empeado con ID " + id));
        return empleadoMapper.toDto(empleado);
    }

    public EmpleadoDto crear(EmpleadoDto dto) {
        Empleado empleado = empleadoMapper.toEntity(dto);
        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    public EmpleadoDto actualizar(Long id, EmpleadoDto dto) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException("No se encontro el empleado con ID " + id));
        empleado.setPuesto(dto.getPuesto());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    public void borrar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNotFoundException("Empleado con ID" + id + " no existe, no se puede borrar");
        }
        empleadoRepository.deleteById(id);
    }

    public List<EmpleadoDto> buscarPorPuesto(String puesto) {
        return empleadoRepository.findByPuesto(puesto).stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> buscarPorAtraccionId(Long atraccionId) {
        return empleadoRepository.buscarPorAtraccionId(atraccionId).stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> buscarPorEjemplo(EmpleadoDto ejemploDto) {
        Empleado ejemplo = empleadoMapper.toEntity(ejemploDto);
        return empleadoRepository.findAll(Example.of(ejemplo)).stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }
}
