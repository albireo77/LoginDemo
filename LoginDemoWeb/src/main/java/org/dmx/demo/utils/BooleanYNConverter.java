package org.dmx.demo.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanYNConverter implements AttributeConverter<Boolean, String> {
	
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return convertBoolToString(value);
    }
    
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return convertStringToBool(value);
    }
    
    public static String convertBoolToString(Boolean value) {
        return Boolean.TRUE.equals(value) ? "Y" : "N";  	
    }
    
    public static Boolean convertStringToBool(String value) {        
    	return "Y".equalsIgnoreCase(value);   	
    }
}
