package by.serzh.beatsub.api.domain.musicfolder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class MusicFolders {

    private List<MusicFolder> musicFolder = new ArrayList<>();

    @JsonGetter("musicFolder")
    public List<MusicFolder> getMusicFolder() {
        return musicFolder;
    }

    @JsonSetter("musicFolder")
    public void setMusicFolders(List<MusicFolder> musicFolder) {
        this.musicFolder = musicFolder;
    }
}
