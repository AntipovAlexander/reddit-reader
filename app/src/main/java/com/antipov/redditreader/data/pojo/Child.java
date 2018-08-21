package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Child {
    @SerializedName("kind")
    public String kind;

    @SerializedName("data")
    public Object data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
