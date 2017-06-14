package com.techandsolve.easymapper4j.descriptors;

/**
 * Especifica el mapeo adecuado para un parametro de salida que devuelva un Cursor y el cual debe ser mapeado a una
 * lista de objetos segun lo especificado en el descriptor de mapeo.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class OutputResultSetDescriptor {
    private String name;
    private MappingDescriptor mappingDescriptor;
    private int paramIndex;

    /**
     * El descriptor de las reglas de mapeo
     * @return 
     */
    public MappingDescriptor getMappingDescriptor() {
        return mappingDescriptor;
    }

    /**
     * El descriptor de las reglas de mapeo
     * @param mappingDescriptor 
     */
    public void setMappingDescriptor(MappingDescriptor mappingDescriptor) {
        this.mappingDescriptor = mappingDescriptor;
    }
    
    /**
     * La clase objetivo del mapeo.
     * @return 
     */
    public Class<?> getTargetClass(){
        return mappingDescriptor.getTargetClass();
    }

    /**
     * El nombre del parametro de salida tal y como esta definido en el procedimiento en base de datos.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * El nombre del parametro de salida tal y como esta definido en el procedimiento en base de datos.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getParamIndex() {
        return paramIndex;
    }

    public void setParamIndex(int paramIndex) {
        this.paramIndex = paramIndex;
    }
}
