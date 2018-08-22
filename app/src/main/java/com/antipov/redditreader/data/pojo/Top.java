package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Top {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Data data;

    /**
     * @return model for unit test
     */
    public static Top getForTest() {
        Top t = new Top();
        Data d = new Data();
        List<Child> children = new ArrayList<>();
        children.add(new Child());
        d.setChildren(children);
        t.setData(d);
        return t;
    }

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
