package com.jaime.apirest.Dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AtraccionDto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El tipo no puede estar vacío")
    private String tipo;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private BigDecimal precio;

    @NotNull(message = "Debe indicar si está activa")
    private Boolean activa;

    @NotNull(message = "La fecha de inauguración es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaInauguracion;

    private List<Long> empleadosIds;   // Lista de IDs, no los objetos completos
    private List<Long> visitantesIds;

    public AtraccionDto() {
    }

    public AtraccionDto( Long id, String nombre, String tipo, BigDecimal precio, Boolean activa, LocalDate fechaInauguracion, List<Long> empleadosIds, List<Long> visitantesIds) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.activa = activa;
        this.fechaInauguracion = fechaInauguracion;
        this.empleadosIds = empleadosIds;
        this.visitantesIds = visitantesIds;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }

    public LocalDate getFechaInauguracion() { return fechaInauguracion; }
    public void setFechaInauguracion(LocalDate fechaInauguracion) { this.fechaInauguracion = fechaInauguracion; }

    public List<Long> getEmpleadosIds() { return empleadosIds; }
    public void setEmpleadosIds(List<Long> empleadosIds) { this.empleadosIds = empleadosIds; }

    public List<Long> getVisitantesIds() { return visitantesIds; }
    public void setVisitantesIds(List<Long> visitantesIds) { this.visitantesIds = visitantesIds; }
}
