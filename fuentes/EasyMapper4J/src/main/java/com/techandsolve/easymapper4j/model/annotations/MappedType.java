package com.techandsolve.easymapper4j.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indica que el tipo marcado con esta anotación, es un tipo de dato que va a ser mapeado por SPMapper desde un
 * procedimiento o funcion almacenada.
 * 
 * (Actualmente es solo una anotacion de marcado, es opcional.)
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MappedType {
    
}
