package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("modhash")
    public String modhash;

    @SerializedName("dist")
    public Integer dist;

    @SerializedName("children")
    public List<Child> children = null;

    @SerializedName("after")
    public String after;

    @SerializedName("before")
    public Object before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }
}
