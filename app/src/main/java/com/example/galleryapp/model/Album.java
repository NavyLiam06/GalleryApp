package com.example.galleryapp.model;

public class Album {
    String name;
    int itemCount = 0;
    String lastItemPath = null;
    int imagesCount = 0;
    int videosCount = 0;

    public Album(String name, int itemCount, String lastItemPath, int imagesCount, int videosCount) {
        this.name = name;
        this.itemCount = itemCount;
        this.lastItemPath = lastItemPath;
        this.imagesCount = imagesCount;
        this.videosCount = videosCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getLastItemPath() {
        return lastItemPath;
    }

    public void setLastItemPath(String lastItemPath) {
        this.lastItemPath = lastItemPath;
    }

    public int getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    public int getVideosCount() {
        return videosCount;
    }

    public void setVideosCount(int videosCount) {
        this.videosCount = videosCount;
    }
}
