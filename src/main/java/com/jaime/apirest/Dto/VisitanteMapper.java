package com.jaime.apirest.Dto;

import com.jaime.apirest.model.Visitante;
import org.springframework.stereotype.Component;

@Component
public class VisitanteMapper {

    public VisitanteDto toDto(Visitante visitante) {
        return new VisitanteDto(
                visitante.getNombre(),
                visitante.getFechaNacimiento(),
                visitante.getEntradasCompradas(),
                null
        );
    }

    public Visitante toEntity(VisitanteDto dto) {
        Visitante visitante = new Visitante();
        visitante.setNombre(dto.getNombre());
        visitante.setFechaNacimiento(dto.getFechaNacimiento());
        visitante.setEntradasCompradas(dto.getEntradasCompradas());
        return visitante;
    }
}

