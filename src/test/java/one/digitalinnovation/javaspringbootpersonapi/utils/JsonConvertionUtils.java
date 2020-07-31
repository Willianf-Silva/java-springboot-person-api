package one.digitalinnovation.javaspringbootpersonapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConvertionUtils {

    // Método para converter um objeto em um arquivo Json
    public static String asJsonString(Object object){
        try{
            ObjectMapper objetctMapper = new ObjectMapper();
            objetctMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objetctMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objetctMapper.registerModules(new JavaTimeModule());

            return objetctMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException((e));
        }
    }
}

