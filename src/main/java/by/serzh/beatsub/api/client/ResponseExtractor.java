package by.serzh.beatsub.api.client;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ResponseExtractor {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T extract(InputStream stream, Class<T> cl) throws IOException {
        Response response = objectMapper.readValue(stream, Response.class);
        SubsonicResponse<T> subsonicResponse = response.<T>getResponse();
        return subsonicResponse.content;
    }

    private static class Response<T> {
        private SubsonicResponse<T> response;

        @JsonGetter("subsonic-response")
        public SubsonicResponse<T> getResponse() {
            return response;
        }

        @JsonSetter("subsonic-response")
        public void setResponse(SubsonicResponse<T> response) {
            this.response = response;
        }
    }

    private static class SubsonicResponse<T> {
        private Status status;
        private String version;
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
