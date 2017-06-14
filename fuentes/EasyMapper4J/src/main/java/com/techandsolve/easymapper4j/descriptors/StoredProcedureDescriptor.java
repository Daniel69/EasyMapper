package com.techandsolve.easymapper4j.descriptors;

import com.techandsolve.easymapper4j.jdbc.ProcedureCall;
import com.techandsolve.easymapper4j.types.ReturnType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Esta es la clase central del modelo interno de datos del sistema SPMapper, esta clase describe completamente
 * una funcion o procedimiento almacenado para su manejo por parte de SPMapper; en esta clase se definen tanto
 * el nombre del procedimiento o funcion, asi tambien como sus parametros de entrada y las reglas de mapeo 
 * correspondientes a cada uno de los cursores de salida y retorno, asi tambien como los parametros que deben
 * ser declarados como los de tipo Lob.
 * 
 * Un descriptor es reutilizable y seguro para acceso concurrente (thread safe) por lo cual no contiene datos definidos 
 * en tiempo de ejecución como son los valores reales de los parametros de entrada o salida.
 * 
 * Tambien contiene el objeto que define la llamada JDBC compilada al procedimiento en base de datos ProcedureCall, el cual 
 * es tambien un objeto reutilizable y seguro para acceso concurrente (thread safe).
 * 
 * 
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class StoredProcedureDescriptor {
    public static final String RETURN_PARAMETER_NAME = "_SPMapper_return_param";
    
    private String name;
    private String packageName;
    private boolean isFunction;
    private String schemaName;
    private boolean withoutMetaDataAccess;
    private List<InputParameterDescriptor> inputParameters;
    private ReturnType returnType;
    private OutputResultSetDescriptor returnDescriptor;
    private Map<String, OutputResultSetDescriptor> outputResultSets;
    private Map<String, OutputLobParameterDescriptor> outputLobParameteres;
    private Map<String, OutputParameterDescriptor> outputParameteres;
    private ProcedureCall call;
    private int returnSqlType;

    /**
     * Retorna el objeto que representa la llamada JDBC al procedimiento en base de datos.
     * @return 
     */
    public ProcedureCall getCall() {
        return call;
    }

    /**
     * Los parametros de salida de tipo Lob
     * @param outputLobParameteres 
     */
    public void setOutputLobParameteres(Map<String, OutputLobParameterDescriptor> outputLobParameteres) {
        this.outputLobParameteres = outputLobParameteres;
    }
    
    /**
     * Define un nuevo parametro de salida de tipo Lob.
     * @param outputLobParameter 
     */
    public void addOutputLobParameter(OutputLobParameterDescriptor outputLobParameter){
        outputLobParameteres.put(outputLobParameter.getName(), outputLobParameter);
    }
    
    /**
     * Retorna el descriptor de un parametro de salida de tipo Lob identificado por name.
     * @param name
     * @return 
     */
    public OutputLobParameterDescriptor getOutputLobParameter(String name){
        return outputLobParameteres.get(name);
    }
    
    /**
     * Indica si el procedimeinto tiene un parametro de salida de tipo Lob identificado con name.
     * @param name
     * @return 
     */
    public boolean hasOutputLobParameter(String name){
        return outputLobParameteres.containsKey(name);
    }
    
    /**
     * Los parametros de salida de tipo Lob
     * @return 
     */
    public Collection<OutputLobParameterDescriptor> getOutputLobParameters(){
        return outputLobParameteres.values();
    }
    
    /**
     * Los parametros de salida.
     * @param outputLobParameteres 
     */
    public void setOutputParameteres(Map<String, OutputParameterDescriptor> outputParameteres) {
        this.outputParameteres = outputParameteres;
    }    
    
    /**
     * Indica si el procedimeinto tiene un parametro de salida identificado con name.
     * @param name
     * @return 
     */
    public boolean hasOutputParameter(String name){
        return outputParameteres.containsKey(name);
    }
    
    /**
     * Los parametros de salida.
     * @return 
     */
    public Collection<OutputParameterDescriptor> getOutputParameters(){
        return outputParameteres.values();
    }    

    /**
     * Establece el objeto que representa la llamada JDBC.
     * @param call 
     */
    public void setCall(ProcedureCall call) {
        this.call = call;
    }

    /**
     * Indica si el procedimiento es una función almacenada (retorna un valor).
     * @return 
     */
    public boolean isFunction() {
        return isFunction;
    }

    /**
     * Define si el procedimiento es una funcion.
     * @param isFunction 
     */
    public void setFunction(boolean isFunction) {
        this.isFunction = isFunction;
    }

    /**
     * El nombre del procedimiento como se espera que este definido en base de datos.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * El nombre del procedimiento como se espera que este definido en base de datos.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * El nombre del paquete en el que se encuentra el procedimiento en base de datos.
     * @return El nombre del paquete o string vacio si no se encuentra en ningun paquete.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * El nombre del paquete en el que se encuentra el procedimiento en base de datos.
     * @param packageName 
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * El nombre del esquema (usuario) dueño del procedimiento almacenado.
     * @return 
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * El nombre del esquema (usuario) dueño del procedimiento almacenado.
     * @param schemaName 
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
       
    /**
     * El tipo de retorno de la funcion (siempre y cuando el procedimiento sea una funcion).
     * @return 
     */
    public ReturnType getReturnType() {
        return returnType;
    }

    /**
     * El tipo de retorno de la funcion (siempre y cuando el procedimiento sea una funcion).
     * @param returnType 
     */
    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }

    /**
     * La definicion de los parametros de entrada.
     * @return 
     */
    public List<InputParameterDescriptor> getInputParameters() {
        return inputParameters;
    }

    /**
     * La definicion de los parametros de entrada.
     * @param inputParameters 
     */
    public void setInputParameters(List<InputParameterDescriptor> inputParameters) {
        this.inputParameters = inputParameters;
    }

    /**
     * La definición de los parametros de salida tipo cursor y que deban ser mapeados.
     * @return 
     */
    public Map<String, OutputResultSetDescriptor> getOutputResultSets() {
        return outputResultSets;
    }

    /**
     * La definición de los parametros de salida tipo cursor y que deban ser mapeados.
     * @param outputResultSets 
     */
    public void setOutputResultSets(Map<String, OutputResultSetDescriptor> outputResultSets) {
        this.outputResultSets = outputResultSets;
    }

    /**
     * El descriptor del valor de retorno en caso que sea un cursor y deba ser mapeado.
     * @return 
     */
    public OutputResultSetDescriptor getReturnDescriptor() {
        return returnDescriptor;
    }

    /**
     * El descriptor del valor de retorno en caso que sea un cursor y deba ser mapeado.
     * @param returnDescriptor 
     */
    public void setReturnDescriptor(OutputResultSetDescriptor returnDescriptor) {
        this.returnDescriptor = returnDescriptor;
    }

    public boolean isWithoutMetaDataAccess() {
        return withoutMetaDataAccess;
    }

    public void setWithoutMetaDataAccess(boolean withoutMetaDataAccess) {
        this.withoutMetaDataAccess = withoutMetaDataAccess;
    }

    public void setReturnSqlType(int sqlType) {
        returnSqlType = sqlType;
    }

    public int getReturnSqlType() {
        return returnSqlType;
    }
    
}