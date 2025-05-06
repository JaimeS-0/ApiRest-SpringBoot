package com.jaime.apirest.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String puesto;
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "atraccion_id")
    private Atraccion atraccion;

    @ManyToMany(mappedBy = "empleados")
    private List<Atraccion> atracciones;

    public Empleado() {
    }

    public Empleado(Long id, String nombre, String puesto, LocalDate fechaContratacion, Atraccion atraccion, List<Atraccion> atracciones) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.atraccion = atraccion;
        this.atracciones = atracciones;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public Atraccion getAtraccion() { return atraccion; }
    public void setAtraccion(Atraccion atraccion) { this.atraccion = atraccion; }

    public List<Atraccion> getAtracciones() { return atracciones; }
    public void setAtracciones(List<Atraccion> atracciones) { this.atracciones = atracciones; }
}
