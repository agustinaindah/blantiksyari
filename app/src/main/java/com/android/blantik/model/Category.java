package com.android.blantik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class Category implements Serializable {

    private Integer categoryId;
    private String categoryTitle;
    private List<Category> categorySub = new ArrayList<Category>();
    private String type;

    public Category() {
    }

    public Category(Integer categoryId, String categoryTitle) {
        this(categoryId, categoryTitle, null);
    }

    public Category(Integer categoryId, String categoryTitle, List<Category> categorySub) {
        this(categoryId, categoryTitle, categorySub, null);
    }

    public Category(Integer categoryId, String categoryTitle, List<Category> categorySub, String type) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categorySub = categorySub;
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public List<Category> getCategorySub() {
        return categorySub;
    }

    public String getType() {
        return type;
    }
}
