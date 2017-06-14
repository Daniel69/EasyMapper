package com.techandsolve.easymapper4j.descriptors;

import com.techandsolve.easymapper4j.types.LobType;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameter;

/**
 * Define los parametros de salida de tipo CLOB o BLOB, los cuales deben ser definidos en los metadatos de definición
 * del proccedimiento.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class OutputLobParameterDescriptor {
    private String name;
    private LobType lobType;
    private int paramIndex;

    public OutputLobParameterDescriptor(OutputLobParameter lobParameter) {
        lobType = lobParameter.type();
        name = lobParameter.name();
        paramIndex = lobParameter.paramIndex();
    }

    public OutputLobParameterDescriptor() {
    }

    /**
     * El tipo de LOB que se espera en el parametro de salida.
     * @return 
     */
    public LobType getLobType() {
        return lobType;
    }

    /**
     * El tipo de LOB que se espera en el parametro de salida.
     * @param lobType 
     */
    public void setLobType(LobType lobType) {
        this.lobType = lobType;
    }

    /**
     * El nombre del parametro de salida tal como esta definido en el procedimiento.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * El nombre del parametro de salida tal como esta definido en el procedimiento.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * El tipo JDBC asociado al tipo de lob que representa el objeto.
     * @return 
     */
    public int getSqlType() {
        return lobType.getSqlType();
    }

    public int getParamIndex() {
        return paramIndex;
    }

    public void setParamIndex(int paramIndex) {
        this.paramIndex = paramIndex;
    }
}
