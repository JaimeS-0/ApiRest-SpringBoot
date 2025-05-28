package com.jaime.apirest.exception;

public class AtraccionNotFoundException extends RuntimeException {

    public AtraccionNotFoundException() {
        super("Atraccion no encontrada");
    }

    public AtraccionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
