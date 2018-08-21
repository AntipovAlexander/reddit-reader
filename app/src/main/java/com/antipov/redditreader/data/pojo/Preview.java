package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Preview {
    @SerializedName("images")
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
