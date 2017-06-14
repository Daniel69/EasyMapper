package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.types.MappingType;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 *
 * @author user
 */
public class ParameterExtractorFactory {
    
    private Class<?> defaultLobHandler = DefaultLobHandler.class;
    private LobHandler lobHandler;
    private Map<MappingType, ParameterExtractor> extractors = new EnumMap<MappingType, ParameterExtractor>(MappingType.class);
    
    public ParameterExtractorFactory() {
        try {
            lobHandler = (LobHandler) defaultLobHandler.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Error insesperado al instanciar el defaultLobHandler",ex);
        }
    }

    public ParameterExtractorFactory(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }
    
    public ParameterExtractor getParameterExtractor(MappingType mappingType){
        if(!extractors.containsKey(mappingType)){
            extractors.put(mappingType, createParameterExtractor(mappingType));
        }
        return extractors.get(mappingType);
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
        extractors.clear();
    }

    private ParameterExtractor createParameterExtractor(MappingType mappingType) {
        if(mappingType == MappingType.DEFAULT)
            return new DefaultParameterExtractor();
        if(mappingType == MappingType.BLOB)
            return new BlobParameterExtractor(lobHandler);
        if(mappingType == MappingType.CLOB)
            return new ClobParameterExtractor(lobHandler);
        throw new RuntimeException("Tipo de mappeo desconocido, error inesperado!");
    }
}
