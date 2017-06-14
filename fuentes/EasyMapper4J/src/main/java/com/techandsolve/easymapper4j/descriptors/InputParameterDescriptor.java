package com.techandsolve.easymapper4j.descriptors;

import com.techandsolve.easymapper4j.types.MappingType;

/**
 * Describe los parametros de entrada del procedimiento almacenado.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class InputParameterDescriptor {
    private String propertyName;
    private String parameterName;
    private Class<?> procedureClass;
    private MappingType type;
    private int sqlType;
    private int paramIndex;

    public InputParameterDescriptor() {
    }

    public InputParameterDescriptor(String propertyName, String parameterName) {
        this.propertyName = propertyName;
        this.parameterName = parameterName;
    }

    /**
     * Nombre del paramtro del procedimiento en base de datos.
     * @return El nombre del paramtro del procedimiento en base de datos.
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Nombre del paramtro del procedimiento en base de datos.
     * @param parameterName 
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * El nombre de la propiedad del JavaBean, la cual contiene el valor del parametro a utilizar en la ejecución.
     * @return El nombre de dicha propiedad.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * El nombre de la propiedad del JavaBean, la cual contiene el valor del parametro a utilizar en la ejecución.
     * @param propertyName 
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * La clase que representa el procedimiento.
     * @return 
     */
    public Class<?> getProcedureClass() {
        return procedureClass;
    }

    /**
     * La clase que representa el procedimiento.
     * @param procedureClass 
     */
    public void setProcedureClass(Class<?> procedureClass) {
        this.procedureClass = procedureClass;
    }

    public MappingType getType() {
        return type;
    }

    public void setType(MappingType type) {
        this.type = type;
    }

    public int getParamIndex() {
        return paramIndex;
    }

    public void setParamIndex(int paramIndex) {
        this.paramIndex = paramIndex;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }
}
