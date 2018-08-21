package com.antipov.redditreader.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Content {

    @SerializedName("author")
    public String author;

    @SerializedName("title")
    public String title;

    @SerializedName("subreddit_name_prefixed")
    public String subredditName;

    @SerializedName("score")
    public int score;

    @SerializedName("thumbnail")
    public String thumbnail;

    @SerializedName("num_comments")
    public int numComments;

    @SerializedName("url")
    public String url;

    @SerializedName("created_utc")
    public long createdUtc;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubredditName() {
        return subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(long createdUtc) {
        this.createdUtc = createdUtc;
    }
}
