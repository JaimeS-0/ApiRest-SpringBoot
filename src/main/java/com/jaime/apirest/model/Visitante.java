package com.jaime.apirest.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDate fechaNacimiento;
    private BigDecimal entradasCompradas;

    @ManyToMany
    @JoinTable(
            name = "visitante_atraccion",
            joinColumns = @JoinColumn(name = "visitante_id"),
            inverseJoinColumns = @JoinColumn(name = "atraccion_id")
    )
    private List<Atraccion> atracciones;

    public Visitante() {
    }

    public Visitante(Long id, String nombre, LocalDate fechaNacimiento, BigDecimal entradasCompradas, List<Atraccion> atracciones) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.entradasCompradas = entradasCompradas;
        this.atracciones = atracciones;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public BigDecimal getEntradasCompradas() { return entradasCompradas; }
    public void setEntradasCompradas(BigDecimal entradasCompradas) { this.entradasCompradas = entradasCompradas; }

    public List<Atraccion> getAtracciones() { return atracciones; }
    public void setAtracciones(List<Atraccion> atracciones) { this.atracciones = atracciones; }
}
