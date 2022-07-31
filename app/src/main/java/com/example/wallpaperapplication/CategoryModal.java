package com.example.wallpaperapplication;

public class CategoryModal {

    private String category;
    private String categoryUrl;


    public CategoryModal(String category, String categoryUrl) {
        this.category = category;
        this.categoryUrl = categoryUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }


}
