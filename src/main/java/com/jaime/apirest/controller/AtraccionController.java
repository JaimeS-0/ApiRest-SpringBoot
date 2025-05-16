package com.jaime.apirest.controller;

import com.jaime.apirest.Dto.AtraccionDto;
import com.jaime.apirest.service.AtraccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/atracciones")
public class AtraccionController {

    private final AtraccionService atraccionService;

    public AtraccionController(AtraccionService atraccionService) {
        this.atraccionService = atraccionService;
    }

    @GetMapping
    public ResponseEntity<List<AtraccionDto>> listarTodas() {
        List<AtraccionDto> lista = atraccionService.obtenerTodas();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Busca una atracción por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atracción encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AtraccionDto.class))),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la atracción",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<AtraccionDto> obtenerPorId(@PathVariable Long id) {
        AtraccionDto dto = atraccionService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AtraccionDto> crear(@RequestBody AtraccionDto dto) {
        AtraccionDto creado = atraccionService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtraccionDto> actualizar(@PathVariable Long id, @RequestBody AtraccionDto dto) {
        AtraccionDto actualizado = atraccionService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        atraccionService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activas")
    public ResponseEntity<List<AtraccionDto>> listarActivas() {
        List<AtraccionDto> lista = atraccionService.listarActivas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/precio-mayor/{precio}")
    public ResponseEntity<List<AtraccionDto>> buscarPorPrecioMayor(@PathVariable BigDecimal precio) {
        List<AtraccionDto> lista = atraccionService.buscarPorPrecioMayorQue(precio);
        return ResponseEntity.ok(lista);
    }
}
