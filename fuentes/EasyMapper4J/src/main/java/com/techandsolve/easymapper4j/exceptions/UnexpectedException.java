package com.techandsolve.easymapper4j.exceptions;

/**
 * Clase que encapsula todos los errores inesperados al interior del framework. Cuando se lanza este excepcion
 * es por una de 3 causas:
 * 1. Un bug al interior del mismo framework que debe ser corregido.
 * 2. Un error generalizado del sistema, tal como un error en la JVM.
 * 3. Un error no tenido en cuenta por SPMapper y que debe ser manejado de alguna manera.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class UnexpectedException extends RuntimeException{

    public UnexpectedException(Throwable cause) {
        super("Ocurrio una Excepcion inesperada por favor reportela.",cause);
    }
}
