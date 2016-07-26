package by.serzh.beatsub.api.client;

import by.serzh.beatsub.api.domain.SubsonicError;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.utils.ExceptionUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ResponseExtractor {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public static <T> T extract(InputStream stream, Class<T> cl) throws IOException, SubsonicException {
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
        ObjectMapper mapper = new ObjectMapper();
        T result = null;

        Map<String, Object> data = mapper.readValue(stream, typeRef);
        Map<String, Object> subsonicResponse = (Map<String, Object>) data.get("subsonic-response");
        Status status = objectMapper.convertValue(subsonicResponse.get("status"), Status.class);
        if(status == Status.OK) {
            Object object = subsonicResponse.get(cl.getSimpleName().toLowerCase());
            if(object != null) {
                result = objectMapper.convertValue(object, cl);
            }
        } else {
            SubsonicError error = objectMapper.convertValue(subsonicResponse.get("error"), SubsonicError.class);
            ExceptionUtils.handle(error);
        }

        return result;
    }

    private enum Status {
        @JsonProperty("ok")
        OK,

        @JsonProperty("failed")
        FAILED;
    }

}
