package by.serzh.beatsub.api.domain.index;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class Index {

    private String name;
    private List<Artist> artist = new ArrayList<>();

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("artist")
    public List<Artist> getArtist() {
        return artist;
    }

    @JsonSetter("artist")
    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }
}
