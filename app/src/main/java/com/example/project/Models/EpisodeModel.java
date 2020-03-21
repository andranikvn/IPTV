package com.example.project.Models;

import org.json.JSONObject;

public class EpisodeModel {
    private String id;
    private int episodeNum;
    private String title;
    private String container_extension;
    private JSONObject info;
    private String customSid;
    private String added;
    private int season;
    private String directSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContainer_extension() {
        return container_extension;
    }

    public void setContainer_extension(String container_extension) {
        this.container_extension = container_extension;
    }

    public JSONObject getInfo() {
        return info;
    }

    public void setInfo(JSONObject info) {
        this.info = info;
    }

    public String getCustomSid() {
        return customSid;
    }

    public void setCustomSid(String customSid) {
        this.customSid = customSid;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getDirectSource() {
        return directSource;
    }

    public void setDirectSource(String directSource) {
        this.directSource = directSource;
    }
}

