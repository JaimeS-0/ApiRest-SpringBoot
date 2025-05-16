package com.jaime.apirest.repository;

import com.jaime.apirest.model.Atraccion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class AtraccionRepositoryTest {

    @Autowired
    private AtraccionRepository atraccionRepository;

    @Test
    @DisplayName("Debe devolver solo las atracciones activas")
    void testFindByActivaTrue() {

        Atraccion activa1 = new Atraccion(null, "Torre", "Caída", new BigDecimal("9.99"), true, LocalDate.of(2020, 1, 1), null, null);
        Atraccion activa2 = new Atraccion(null, "Cohete", "Rápida", new BigDecimal("12.99"), true, LocalDate.of(2021, 6, 15), null, null);
        Atraccion inactiva = new Atraccion(null, "Barco", "Lenta", new BigDecimal("5.00"), false, LocalDate.of(2015, 3, 10), null, null);

        atraccionRepository.saveAll(List.of(activa1, activa2, inactiva));


        // ACT (ejecutamos el metodo)
        List<Atraccion> activas = atraccionRepository.findByActivaTrue();

        // ASSERT (comprobamos resultado)
        assertThat(activas).hasSize(2);
        assertThat(activas).allMatch(a -> a.getActiva());


    }

}
