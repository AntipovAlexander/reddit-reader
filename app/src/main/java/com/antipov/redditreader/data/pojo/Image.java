package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {
    @SerializedName("source")
    private Resolution source;

    @SerializedName("resolutions")
    private List<Resolution> resolutions;

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    public Resolution getSource() {
        return source;
    }

    public void setSource(Resolution source) {
        this.source = source;
    }
}
