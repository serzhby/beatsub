package by.serzh.beatsub.api.domain.musicfolder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MusicFolder {

    private Integer id;

    private String name;

    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }
}
