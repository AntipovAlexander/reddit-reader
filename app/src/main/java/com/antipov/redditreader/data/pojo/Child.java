package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Child {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Content data;

    public Child() {
    }

    public Child(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }
}
