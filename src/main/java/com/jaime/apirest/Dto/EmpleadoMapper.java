package com.jaime.apirest.Dto;

import com.jaime.apirest.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoDto toDto(Empleado empleado) {
        return new EmpleadoDto(
                empleado.getNombre(),
                empleado.getPuesto(),
                empleado.getFechaContratacion(),
                empleado.getAtraccion() != null ? empleado.getAtraccion().getId() : null
        );
    }

    public Empleado toEntity(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setPuesto(dto.getPuesto());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return empleado;
    }
}
