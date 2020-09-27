package com.project.allinonenews;

public class NewsDetailsEntity {
    String title, description, url, urlToImage, publishedAt, content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsDetailsEntity(String title, String description, String url, String urlToImage, String publishedAt, String content){
        this.title = title;
        this.content=content;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;

    }
}
