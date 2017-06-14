package com.techandsolve.easymapper4j.descriptors;

import com.techandsolve.easymapper4j.procedures.annotations.OutputParameter;

/**
 *
 * @author Daniel Bustamante Ospina <daniel.bustamante>
 */
public class OutputParameterDescriptor {
    private String name;
    private int sqlType;
    private int paramIndex;

    public OutputParameterDescriptor(OutputParameter outputParameter) {
        name = outputParameter.name();
        sqlType = outputParameter.sqlType();
        paramIndex = outputParameter.paramIndex();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
