package com.techandsolve.easymapper4j.exceptions;

/**
 * Clase utilitaria que define los mensajes detallados de error.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public final class ErrorMessages {
    
    public static final String NO_GETTER_INPUT_PARAMETER = "No se encontro el metodo accesor para el parametro de entrada: %s, asociado a la propiedad: %s";
    public static final String NO_ACCESS_GETTER_INPUT_PARAMETER = "El metodo accesor para el parametro de entrada: %s, asociado a la propiedad: %s. No es Accesible.";
    public static final String GETTER_INVOCATION_ERROR = "Se presento un error trantando de acceder a la propiedad: %s, del objeto: %s";
    public static final String MAPPING_INSTANTIATION_EXCEPTION = "Ocurrio un error al tratar de instanciar la clase: %s, para realizar el mapeo.";
    public static final String MAPPING_INSTANTIATION_ILLEGAL_ACCESS = "No se tiene acceso al constructor por defecto de la clase: %s, para realizar el mapeo.";
    public static final String NULL_DATA_SOURCE = "El DataSource pasado en el constructor de StoredProcedureExecutor no pude ser null.";
    public static final String NULL_JDBC_TEMPLATE = "El JdbcTemplate pasado en el constructor de StoredProcedureExecutor no pude ser null.";
    public static final String NO_STORED_PROCEDURE_AT = "La clase del objeto no representa un procedimeinto almacenado, no tiene la anotacion obligatoria StoredProcedure!";
    public static final String NO_FUNCTION_RETURN_DEF = "Todos los procedimientos de tipo función deben tener la anotación FunctionReturnValue!";
    public static final String ERROR_GETTING_MAPPED_PROPERTY_TYPE = "No se pudo obtener el tipo de dato de la propiedad indicada en el mapeo.";
    public static final String OUTPUT_PARAMETER_NOT_FOUND = "No se encuentra el parametro de salida %s, por favor verifique la peticion.";
    public static final String TYPE_CONVERSION_ERROR = "Ocurrio un error al tratar de convertir del tipo: %s, al tipo: %s";
    public static final String UNDECLARED_RESULTSLIST = "El result set requerido (%s) no fue declarado en la definicion del procedimiento.";   
    public static final String UNDECLARED_LOB_OBJECT = "El objeto LOB (CLOB o BLOB) requerido (%s) no fue declarado en la definicion del procedimiento.";    
    public static final String DIFFERENT_RESULTSLIST_TYPE = "El tipo de dato requerido (%s) es diferente al declarado (%s) para la lista de resultados. Indique el tipo correcto!";   
    public static final String NO_SCALAR_RETURN_TYPE = "El tipo de retorno de la funcion no fue declarado como un SCALAR. Use getReturnedResultList para acceder a la lista de resultados.";  
    public static final String NO_CURSOR_RETURN_TYPE = "El tipo de retorno de la funcion no fue declarado como un CURSOR. Use getReturnedValue para acceder al resultado escalar.";      
    public static final String PROCEDURE_EXECUTION_EXCEPTION = "La ejecucion del procedimiento (%s) en base de datos ha devuelto una excepcion. Verifique que la declaracion y los parametros coincidan con la definicion en base de datos.";
    public static final String GENERIC_MAPPING_EXCEPTION = "Ocurrio una excepcion al momento de realizar el mapeo al tipo de dato objetivo.";
    
    private ErrorMessages() {}
}
