package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica un uso invalido del API publica del sistema SPMapper.
 * Por lo general indica un error de programación en la aplicacion cliente de SPMapper.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class InvalidAPIUsage extends ExpectedException{

    public InvalidAPIUsage() {
    }

    public InvalidAPIUsage(String message) {
        super(message);
    }

    public InvalidAPIUsage(Throwable cause) {
        super(cause);
    }

    public InvalidAPIUsage(String message, Throwable cause) {
        super(message, cause);
    }
}