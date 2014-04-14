package com.nutraspace.search.json;

/**
 * @author Arun
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSONUtils {

    private static Logger logger= LoggerFactory.getLogger(JSONUtils.class);

    //Apparently, this is thread-safe
    public static ObjectMapper MAPPER = new ObjectMapper(){{
        setVisibilityChecker(getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }};

    public static String toJSON(Object object) {
        String jsonString="";
        try {
            jsonString= MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return jsonString;
    }

    public static <T> T toObject(String json, Class <T> type){

        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            logger.error("Original JSON : "+json);
            logger.error(e.getMessage(), e);
        }

        return null;

    }

    public static <T> T toObject(String json, JavaType type){

        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            logger.error("Original JSON : "+json);
            logger.error(e.getMessage(), e);
        }

        return null;

    }

    public static <T> T toObject(String json, TypeReference typeReference){

        try {
            return MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("Original JSON : "+json);
            logger.error(e.getMessage(), e);
        }

        return null;

    }
}