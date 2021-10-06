package com.flabbergast.herokuapp;

public class Category {
    private String category;
    private int level;

    public Category(String category, int level) {
        this.category = category;
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
