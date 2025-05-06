package com.jaime.apirest.Dto;

import com.jaime.apirest.model.Atraccion;
import org.springframework.stereotype.Component;

@Component
public class AtraccionMapper {

    public AtraccionDto toDto(Atraccion atraccion) {
        return new AtraccionDto(
                atraccion.getNombre(),
                atraccion.getTipo(),
                atraccion.getPrecio(),
                atraccion.getActiva(),
                atraccion.getFechaInauguracion(),
                null,
                null
        );
    }

    public Atraccion toEntity(AtraccionDto dto) {
        Atraccion atraccion = new Atraccion();
        atraccion.setNombre(dto.getNombre());
        atraccion.setTipo(dto.getTipo());
        atraccion.setPrecio(dto.getPrecio());
        atraccion.setActiva(dto.getActiva());
        atraccion.setFechaInauguracion(dto.getFechaInauguracion());
        return atraccion;
    }
}