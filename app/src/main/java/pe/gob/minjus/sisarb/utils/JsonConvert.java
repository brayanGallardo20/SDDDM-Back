package pe.gob.minjus.sisarb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonConvert {

    private JsonConvert() {
    }

    public static String objectToJsonString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        log.error(e.getMessage());
            return null;
        }

    }
}
