package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.jdbc.ProcedureCall;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.exceptions.ExpectedException;
import com.techandsolve.easymapper4j.exceptions.UnexpectedException;
import com.techandsolve.easymapper4j.jdbc.JdbcTemplateSupport;
import com.techandsolve.easymapper4j.parameters.ParameterExtractorFactory;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.result.ProcedureResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;

/**
 * Clase encargada de ejecutar los procedimientos almacenados, ya sean funciones o procedimientos.
 * La importancia de esta clase radica en que es la intefez publica del sistema SPMapper con la que interactua 
 * con otros sistemas. El uso esperado de esta clase es que se cree una instancia (Singleton) por cada DataSource de la aplicación
 * cliente, esto con el fin de aprovechar las ventajas de la cache interna de procedimientos compilados; esto quiere decir 
 * que una ves compilado un procedimiento en base a los metadatos, este se almacena en una cache para no tener que 
 * vovlerlo a construir en cada llamada.
 * Las instancias de esta clase son seguras para el acceso concurrente (thread safe) y  preparadas para el manejo 
 * transaccional. 
 * Respecto al manejo transaccional, una instancia de esta clase se comporta exactamente igual que una instancia 
 * de JdbcTemplate ya que esta delega el acceso a datos en dicha clase, la cual usa DataSourceUtils para obtener la conexion,
 * de manera que obtiene la conexion adecuada si se esta ejecutando en un entorno transaccional.
 * 
 * La funcionalidad ofrecida por esta clase se centra en dos metodos principales executeFunction y executeProcedure,
 * para realizar la ejecucion de funciones y procedimientos almacenados respectivamente.
 * 
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class StoredProcedureExecutor {
    
    private JdbcTemplateSupport jdbcTemplateSupport;
    private StoredProcedureFactory storedProcedureFactory;
    private ParameterExtractorFactory parameterExtractorFactory;

    public StoredProcedureExecutor(DataSource dataSource) {
        if(dataSource == null){
            throw new IllegalArgumentException(ErrorMessages.NULL_DATA_SOURCE);
        }  
        
        jdbcTemplateSupport = new JdbcTemplateSupport(dataSource);
        storedProcedureFactory = new StoredProcedureFactory(jdbcTemplateSupport);
        parameterExtractorFactory = new ParameterExtractorFactory();
    }


    /**
     * Este metodo recibe un objeto de una clase que represente una funcion almacenada, esto quiere decir que la 
     * clase este anotada con @StoredProcedure y el parametro isFunction=true, la clase del objeto function debe representar
     * una funcion bien definida (seguir las reglas de sintaxis para su definición), de lo contrario se lanzará una excepción
     * IllegalProcedureDeclaration.
     * @param function
     * @return un objeto FunctionResult con lo que se tiene acceso al resultado de la ejecución de la función.
     */
    public FunctionResult executeFunction(Object function){
        try{
            return new FunctionResult(getProcedureDescriptor(function), execute(function));
        }catch(ExpectedException ex){
            throw ex;
        }catch(Exception ex){
            throw new UnexpectedException(ex);
        } 
    }
    
    /**
     * Este metodo recibe un objeto de una clase que represente un procedimiento almacenado, esto quiere decir que la 
     * clase este anotada con @StoredProcedure y el parametro isFunction=false, la clase del objeto procedure debe representar
     * un procedimiento bien definido (seguir las reglas de sintaxis para su definición), de lo contrario se lanzará una excepción
     * IllegalProcedureDeclaration.
     * @param function
     * @return un objeto ProcedureResult con lo que se tiene acceso al resultado de la ejecución del procedimiento.
     */    
    public ProcedureResult executeProcedure(Object procedure){
        try{
            return new ProcedureResult(getProcedureDescriptor(procedure), execute(procedure));
        }catch(ExpectedException ex){
            throw ex;
        }catch(Exception ex){
            throw new UnexpectedException(ex);
        }      
    }
    
    /*
     * Realiza la ejecucion del procedimiento o función y retorna un Map de resultados. 
     */
    private Map<String, Object> execute(Object storedProcedure){
        StoredProcedureDescriptor storedProcedureDescriptor = getProcedureDescriptor(storedProcedure);
        Map<String, Object> inputParameters = getInputParameters(storedProcedure, storedProcedureDescriptor);
        ProcedureCall call = storedProcedureDescriptor.getCall();   
        return call.execute(inputParameters);
    }
    
    /*
     * Extrae los parametros de entrada como un Map, en base al objeto y al descriptor.
     */
    private Map<String, Object> getInputParameters(Object procedure, StoredProcedureDescriptor spDescriptor){
        List<InputParameterDescriptor> inputParameters = spDescriptor.getInputParameters();
        Map<String, Object> parameters = new HashMap<String, Object>();
        for(InputParameterDescriptor descriptor : inputParameters){        
            parameters.put(descriptor.getParameterName(), getInputParameterValue(procedure, descriptor));
        }
        return parameters;
    } 
    
    /*
     * Extrae el valor de un parametro de entrada del objeto pasado como argumento.
     */
    private Object getInputParameterValue(Object procedure, InputParameterDescriptor descriptor){
        return parameterExtractorFactory.getParameterExtractor(descriptor.getType())
                .getInputParameterValue(procedure, descriptor);
    }
    
    /**
     * Permite configurar un LobHandler para el manejo de Lobs en bases de datos que necesiten un LobHandler especifico
     * como por ejemplo versiones antiguas de Oracle Ej 9i o algunas de la 10i.
     * @param lobHandler 
     */
    public void setLobHandler(LobHandler lobHandler){
        parameterExtractorFactory.setLobHandler(lobHandler);
    }  
    
    /**
     * Es necesario configurarlo cuando se utiliza un LobHandler especifico, por ejemplo cuando se utiliza 
     * OracleLobHandler, se hace necesario especificar un NativeJdbcExtractor como CommonsDbcpNativeJdbcExtractor
     * o WebSphereNativeJdbcExtractor dependiendo del DataSource utilizado.
     * @param nativeJdbcExtractor 
     */
    public void setNativeJdbcExtractor(NativeJdbcExtractor nativeJdbcExtractor){
        jdbcTemplateSupport.setNativeJdbcExtractor(nativeJdbcExtractor);
    }
    
    /*
     * Retorna el descriptor del procedimeinto representado por los metadatos de la clase del objeto.
     */
    private StoredProcedureDescriptor getProcedureDescriptor(Object procedure){
        return storedProcedureFactory.getProcedureDescriptor(procedure.getClass());
    }    
    
}
