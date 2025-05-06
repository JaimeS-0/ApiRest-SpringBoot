package com.jaime.apirest.repository;


import com.jaime.apirest.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

    // Consulta derivada del nombre
    List<Visitante> findByNombreContainingIgnoreCase(String nombre);

    // Consulta JPQL
    @Query("SELECT v FROM Visitante v JOIN v.atracciones a WHERE a.id = :atraccionId")
    List<Visitante> buscarPorAtraccionId(Long atraccionId);
}
