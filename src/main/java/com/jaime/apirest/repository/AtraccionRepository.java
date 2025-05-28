package com.jaime.apirest.repository;


import com.jaime.apirest.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtraccionRepository extends JpaRepository<Atraccion, Long> {

    // Consulta derivada del nombre
    List<Atraccion> findByActivaTrue();

    // Consulta JPQL
    @Query("SELECT a FROM Atraccion a WHERE a.precio > :precioMinimo")
    List<Atraccion> buscarPorPrecioMayorQue(java.math.BigDecimal precioMinimo);

    // Query by Example (QBE) → Spring Data lo hace automáticamente, no necesita metodo aquí.
}
