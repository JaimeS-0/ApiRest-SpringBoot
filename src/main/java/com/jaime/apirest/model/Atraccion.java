package com.jaime.apirest.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Atraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private BigDecimal precio;
    private Boolean activa;
    private LocalDate fechaInauguracion;

    @ManyToMany(mappedBy = "atracciones")
    private List<Visitante> visitantes;

    @ManyToMany
    @JoinTable(
            name = "atraccion_empleado",
            joinColumns = @JoinColumn(name = "atraccion_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleados;

    public Atraccion() {
    }

    public Atraccion(Long id, String nombre, String tipo, BigDecimal precio, Boolean activa, LocalDate fechaInauguracion, List<Visitante> visitantes, List<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.activa = activa;
        this.fechaInauguracion = fechaInauguracion;
        this.visitantes = visitantes;
        this.empleados = empleados;
    }

    public Atraccion(String carruselMágico, String familiar, BigDecimal bigDecimal, boolean b, LocalDate of) {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public List<Visitante> getVisitantes() { return visitantes; }
    public void setVisitantes(List<Visitante> visitantes) { this.visitantes = visitantes; }

    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }
}
