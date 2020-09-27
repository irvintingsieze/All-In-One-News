package com.project.allinonenews;

public class NewsEntity {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


    String id, name, description, url, category;

    public NewsEntity(String id, String name, String description, String url, String category){
        this.id = id;
        this.name=name;
        this.description = description;
        this.url = url;
        this.category = category;

    }
}
