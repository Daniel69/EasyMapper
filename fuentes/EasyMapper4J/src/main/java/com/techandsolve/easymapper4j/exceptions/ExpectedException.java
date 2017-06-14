package com.techandsolve.easymapper4j.exceptions;

/**
 * Esta es la clase base para todas las excepciones esperadas en el proceso y cuya causa sea conocida.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ExpectedException extends RuntimeException{

    public ExpectedException() {
    }

    public ExpectedException(String message) {
        super(message);
    }

    public ExpectedException(Throwable cause) {
        super(cause);
    }

    public ExpectedException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
