package com.example.project.Models;

import java.net.URL;

public class TvChannels {

    private int num;

    private String channelName;

    private String streamType;

    private int streamId;

    private String streamIcon;

    private int epgChannelId;

    private String added;

    private String categoryId;

    private  String customSid;

    private  int tvArchive;

    private String directSource;

    private int tvArchiveDuration;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getStreamIcon() {
        return streamIcon;
    }

    public void setStreamIcon(String streamIcon) {
        this.streamIcon = streamIcon;
    }

    public int getEpgChannelId() {
        return epgChannelId;
    }

    public void setEpgChannelId(int epgChannelId) {
        this.epgChannelId = epgChannelId;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
