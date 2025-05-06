package com.jaime.apirest.repository;


import com.jaime.apirest.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Consulta derivada del nombre
    List<Empleado> findByPuesto(String puesto);

    // Consulta JPQL
    @Query("SELECT e FROM Empleado e WHERE e.atraccion.id = :atraccionId")
    List<Empleado> buscarPorAtraccionId(Long atraccionId);
}
