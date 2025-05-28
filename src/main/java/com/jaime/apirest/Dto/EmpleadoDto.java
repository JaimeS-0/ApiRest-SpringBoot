package com.jaime.apirest.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmpleadoDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El puesto no puede estar vacío")
    private String puesto;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaContratacion;

    @NotNull(message = "Debe asignar una atracción al empleado")
    private Long atraccionId;

    public EmpleadoDto() { }

    public EmpleadoDto(String nombre, String puesto, LocalDate fechaContratacion, Long atraccionId) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.atraccionId = atraccionId;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public Long getAtraccionId() { return atraccionId; }
    public void setAtraccionId(Long atraccionId) { this.atraccionId = atraccionId; }
}
