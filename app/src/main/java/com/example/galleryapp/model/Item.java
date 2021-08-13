package com.example.galleryapp.model;

public class Item {
    long id;
    String name;
    long addedDate;
    String path;
    int duration;
    boolean isHeader = false;
    boolean isVideo = false;
    int position = -1;
    public Item(long id, String name, long addedDate, String path, int duration, boolean isHeader, boolean isVideo, int position) {
        this.id = id;
        this.name = name;
        this.addedDate = addedDate;
        this.path = path;
        this.duration = duration;
        this.isHeader = isHeader;
        this.isVideo = isVideo;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(long addedDate) {
        this.addedDate = addedDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
