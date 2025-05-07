package com.jaime.apirest.config;

import com.jaime.apirest.model.Atraccion;
import com.jaime.apirest.model.Empleado;
import com.jaime.apirest.model.Visitante;
import com.jaime.apirest.repository.AtraccionRepository;
import com.jaime.apirest.repository.EmpleadoRepository;
import com.jaime.apirest.repository.VisitanteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final AtraccionRepository atraccionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VisitanteRepository visitanteRepository;

    public InitData(AtraccionRepository atraccionRepository, EmpleadoRepository empleadoRepository, VisitanteRepository visitanteRepository) {
        this.atraccionRepository = atraccionRepository;
        this.empleadoRepository = empleadoRepository;
        this.visitanteRepository = visitanteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Visitantes
        Visitante visitante1 = new Visitante(null, "Juan Pérez", LocalDate.of(2000, 5, 10), new BigDecimal("2"), null);
        Visitante visitante2 = new Visitante(null, "Ana Gómez", LocalDate.of(1995, 8, 15), new BigDecimal("5"), null);
        Visitante visitante3 = new Visitante(null, "Carlos Sánchez", LocalDate.of(1988, 1, 20), new BigDecimal("3"), null);
        visitanteRepository.saveAll(List.of(visitante1, visitante2, visitante3));

        // Empleados
        Empleado empleado1 = new Empleado(null, "Luis Torres", "Operador", LocalDate.of(2020, 3, 1), null, null);
        Empleado empleado2 = new Empleado(null, "Marta Ruiz", "Mantenimiento", LocalDate.of(2018, 7, 20), null, null);
        Empleado empleado3 = new Empleado(null, "Pedro López", "Supervisor", LocalDate.of(2019, 11, 5), null, null);
        empleadoRepository.saveAll(List.of(empleado1, empleado2, empleado3));

        // Atracciones
        Atraccion atraccion1 = new Atraccion(null, "Montaña Rusa", "Adrenalina", new BigDecimal("15.50"), true, LocalDate.of(2015, 6, 1), List.of(visitante1, visitante2), List.of(empleado1, empleado2));
        Atraccion atraccion2 = new Atraccion(null, "Carrusel", "Familiar", new BigDecimal("5.00"), true, LocalDate.of(2010, 4, 15), List.of(visitante2, visitante3), List.of(empleado3));
        atraccionRepository.saveAll(List.of(atraccion1, atraccion2));

        // Nueva atracción SIN empleados ni visitantes (SE PUEDE BORRAR)
        Atraccion a3 = new Atraccion(null, "Caballitos", "De Caballeria", new BigDecimal("10.00"), true, LocalDate.of(2010, 4, 15), null, null);
        atraccionRepository.save(a3);
        System.out.println("✅ Guardada atracción: ID = " + a3.getId() + ", Nombre = " + a3.getNombre());



        // Asociar atracciones a empleados
        empleado1.setAtraccion(atraccion1);
        empleado2.setAtraccion(atraccion1);
        empleado3.setAtraccion(atraccion2);
        empleadoRepository.saveAll(List.of(empleado1, empleado2, empleado3));

        // Asociar atracciones a visitantes
        visitante1.setAtracciones(List.of(atraccion1));
        visitante2.setAtracciones(List.of(atraccion1, atraccion2));
        visitante3.setAtracciones(List.of(atraccion2));
        visitanteRepository.saveAll(List.of(visitante1, visitante2, visitante3));

        System.out.println(">>> Datos iniciales cargados correctamente 🚀🚀🚀🚀");
    }
}
