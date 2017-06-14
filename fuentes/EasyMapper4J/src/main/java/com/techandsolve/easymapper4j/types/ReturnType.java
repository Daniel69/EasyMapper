package com.techandsolve.easymapper4j.types;

/**
 * Especifica el tipo de retorno de una función almacenada, este puede ser un SCALAR, es decir un valor individual,
 * o un CURSOR_REF, es decir un ResultSet con uno o más registros
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public enum ReturnType {
    SCALAR,
    CURSOR_REF
}
