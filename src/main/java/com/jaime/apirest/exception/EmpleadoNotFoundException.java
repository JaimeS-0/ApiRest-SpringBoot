package com.jaime.apirest.exception;

public class EmpleadoNotFoundException extends RuntimeException{
    public EmpleadoNotFoundException() {
        super("Empleado no encontrado");
    }

    public EmpleadoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
