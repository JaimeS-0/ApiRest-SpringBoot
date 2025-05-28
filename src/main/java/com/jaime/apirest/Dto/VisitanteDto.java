package com.jaime.apirest.Dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VisitanteDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate fechaNacimiento;

    @NotNull(message = "Debe indicar las entradas compradas")
    @DecimalMin(value = "0.0", inclusive = false, message = "Las entradas deben ser mayores que 0")
    private BigDecimal entradasCompradas;

    private List<Long> atraccionesIds;

    public VisitanteDto() { }

    public VisitanteDto(String nombre, LocalDate fechaNacimiento, BigDecimal entradasCompradas, List<Long> atraccionesIds) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.entradasCompradas = entradasCompradas;
        this.atraccionesIds = atraccionesIds;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public BigDecimal getEntradasCompradas() { return entradasCompradas; }
    public void setEntradasCompradas(BigDecimal entradasCompradas) { this.entradasCompradas = entradasCompradas; }

    public List<Long> getAtraccionesIds() { return atraccionesIds; }
    public void setAtraccionesIds(List<Long> atraccionesIds) { this.atraccionesIds = atraccionesIds; }
}
