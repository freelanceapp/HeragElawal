package com.creative.share.apps.heragelawal.models;

import java.io.Serializable;
import java.util.List;

public class CompanyDataModel implements Serializable {

    private int current_page;
    private List<CompanyModel> data;

    public int getCurrent_page() {
        return current_page;
    }

    public List<CompanyModel> getData() {
        return data;
    }

    public static class CompanyModel implements Serializable
    {
        private int id;
        private String name;
        private String logo;
        private String phone;
        private String email;
        private String details;
        private String latitude;
        private String longitude;
        private int views_count;
        private int category_id;
        private int user_id;
        private String user_name;
        private String user_email;
        private String user_phone;
        private String user_avatar;
        private String cat_title;
        private String cat_image;
        private String cat_icon;
        private String cat_type;
        private int ads_count;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLogo() {
            return logo;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getDetails() {
            return details;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public int getViews_count() {
            return views_count;
        }

        public int getCategory_id() {
            return category_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public String getCat_title() {
            return cat_title;
        }

        public String getCat_image() {
            return cat_image;
        }

        public String getCat_icon() {
            return cat_icon;
        }

        public String getCat_type() {
            return cat_type;
        }

        public int getAds_count() {
            return ads_count;
        }
    }
}
