package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Top {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
