package com.jaime.apirest.exception;

public class VisitanteNotFoundException extends RuntimeException{

    public VisitanteNotFoundException () {
        super("Visitante no Encontrado");
    }

    public VisitanteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
