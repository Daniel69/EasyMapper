package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;

/**
 *
 * @author user
 */
public class DefaultParameterExtractor extends ParameterExtractor{

    @Override
    public Object getInputParameterValue(Object procedure, InputParameterDescriptor descriptor) {
        return getSimpleInputParameterValue(procedure, descriptor);
    }
    
}
