package by.serzh.beatsub.api.domain.index;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class Indexes {
    private Long lastModified;
    private String ignoredArticles;
    private List<Index> index = new ArrayList<>();

    @JsonGetter("index")
    public List<Index> getIndex() {
        return index;
    }

    @JsonSetter("index")
    public void setIndex(List<Index> index) {
        this.index = index;
    }

    @JsonGetter("lastModified")
    public Long getLastModified() {
        return lastModified;
    }

    @JsonSetter("lastModified")
    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    @JsonGetter("ignoredArticles")
    public String getIgnoredArticles() {
        return ignoredArticles;
    }

    @JsonSetter("ignoredArticles")
    public void setIgnoredArticles(String ignoredArticles) {
        this.ignoredArticles = ignoredArticles;
    }
}
