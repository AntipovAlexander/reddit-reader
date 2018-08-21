package com.antipov.redditreader.data.pojo;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Content {

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("subreddit_name_prefixed")
    private String subredditName;

    @SerializedName("score")
    private int score;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("num_comments")
    private int numComments;

    @SerializedName("url")
    private String url;

    @SerializedName("created_utc")
    private long createdUtc;

    @SerializedName("preview")
    private Preview preview;

    @Nullable
    public List<Resolution> getImagesFromPreview() {
        try {
            return preview.getImages().get(0).getResolutions();
        } catch (NullPointerException e) {
            // return null if one of these elements in chain is null
            Log.e(getClass().getSimpleName(), e.getLocalizedMessage());
            return null;
        }
    }

    @Nullable
    public Resolution getSource() {
        try {
            return preview.getImages().get(0).getSource();
        } catch (NullPointerException e) {
            // return null if one of these elements in chain is null
            Log.e(getClass().getSimpleName(), e.getLocalizedMessage());
            return null;
        }
    }


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
