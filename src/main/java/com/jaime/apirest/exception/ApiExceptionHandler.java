package com.jaime.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    // Captura cualquier AtraccionNotFoundException que se lance en tu app.

    @ExceptionHandler(AtraccionNotFoundException.class)
    public ProblemDetail manejarAtraccionNoEncontrada(AtraccionNotFoundException at) {

        ProblemDetail detalle = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detalle.setTitle("Atraccion no encontrada");
        detalle.setDetail(at.getMessage());
        return detalle;
    }

    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ProblemDetail empleadoNoEncontrado(EmpleadoNotFoundException em) {
        ProblemDetail detalle = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detalle.setTitle("Empleado no encontrado");
        detalle.setDetail(em.getMessage());
        return detalle;
    }

    @ExceptionHandler(VisitanteNotFoundException.class)
    public ProblemDetail visitanteNoEncontrado(VisitanteNotFoundException vi) {
        ProblemDetail detalle = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detalle.setTitle("Visitante no encontrado");
        detalle.setDetail(vi.getMessage());
        return detalle;
    }
}
