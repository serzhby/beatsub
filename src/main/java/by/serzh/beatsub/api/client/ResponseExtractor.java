package by.serzh.beatsub.api.client;

import by.serzh.beatsub.api.domain.SubsonicError;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    public static <T> T extract(InputStream stream, Class<T> cl) throws IOException {
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
        }

        return result;
    }

    private static class SubsonicResponse<T> {
        private Status status;
        private String version;
        private SubsonicError error;
        private T content;

        @JsonGetter("status")
        public Status getStatus() {
            return status;
        }

        @JsonSetter("status")
        public void setStatus(Status status) {
            this.status = status;
        }

        @JsonGetter("version")
        public String getVersion() {
            return version;
        }

        @JsonSetter("version")
        public void setVersion(String version) {
            this.version = version;
        }

        @JsonGetter("content")
        public T getContent() {
            return content;
        }

        @JsonSetter("content")
        public void setContent(T content) {
            this.content = content;
        }
    }

    private enum Status {
        @JsonProperty("ok")
        OK,

        @JsonProperty("failed")
        FAILED;
    }

}
