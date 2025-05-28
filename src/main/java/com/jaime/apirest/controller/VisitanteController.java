package com.jaime.apirest.controller;

import com.jaime.apirest.Dto.VisitanteDto;
import com.jaime.apirest.service.VisitanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitantes")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    public ResponseEntity<List<VisitanteDto>> listarTodos() {
        return ResponseEntity.ok(visitanteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(visitanteService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<VisitanteDto> crear(@RequestBody VisitanteDto dto) {
        VisitanteDto creado = visitanteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitanteDto> actualizar(@PathVariable Long id, @RequestBody VisitanteDto dto) {
        return ResponseEntity.ok(visitanteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        visitanteService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<VisitanteDto>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(visitanteService.buscarPorNombre(nombre));
    }
}

