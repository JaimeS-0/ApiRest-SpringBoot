package com.jaime.apirest.controller;

import com.jaime.apirest.model.Atraccion;
import com.jaime.apirest.repository.AtraccionRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AtraccionGraphQLController {

    private final AtraccionRepository atraccionRepository;

    public AtraccionGraphQLController(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    @QueryMapping
    public List<Atraccion> atracciones() {
        System.out.println("eNTRPPPPPPPPPPPPPPPPP");
        return atraccionRepository.findAll();
    }
}
