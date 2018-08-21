package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Child {
    @SerializedName("kind")
    public String kind;

    @SerializedName("data")
    public Content data;

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
