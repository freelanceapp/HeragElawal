package com.creative.share.apps.heragelawal.models;

import java.io.Serializable;
import java.util.List;

public class SubCategoryDataModel implements Serializable {

    private List<SubCategoryModel> data;

    public List<SubCategoryModel> getData() {
        return data;
    }

    public static class SubCategoryModel implements Serializable
    {
        private int id;
        private String title;
        private String icon;

        public SubCategoryModel() {
        }

        public SubCategoryModel(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getIcon() {
            return icon;
        }
    }
}
