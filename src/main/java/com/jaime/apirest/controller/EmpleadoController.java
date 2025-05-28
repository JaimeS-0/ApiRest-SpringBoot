package com.jaime.apirest.controller;

import com.jaime.apirest.Dto.EmpleadoDto;
import com.jaime.apirest.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // Aquí puedes agregar los métodos para manejar las solicitudes HTTP (GET, POST, PUT, DELETE)

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> listarTodos() {
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDto> crear(@RequestBody EmpleadoDto dto) {
        EmpleadoDto creado = empleadoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> actualizar(@PathVariable Long id, @RequestBody EmpleadoDto dto) {
        return ResponseEntity.ok(empleadoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        empleadoService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/puesto/{puesto}")
    public ResponseEntity<List<EmpleadoDto>> buscarPorPuesto(@PathVariable String puesto) {
        return ResponseEntity.ok(empleadoService.buscarPorPuesto(puesto));
    }
}
