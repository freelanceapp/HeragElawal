package com.creative.share.apps.heragelawal.models;

import java.io.Serializable;

public class NotificationDataModel implements Serializable {
    private int current_page;

    public class NotificationModel implements Serializable
    {
        private int id;
        private int from_user_id;
        private int to_user_id;
        private int notification_type;
        private int action_type;
        private int is_read;
        private int notification_time;



    }


    public class NotificationBody implements Serializable
    {
        private int id;
        private String title;
        private String body;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }
    }

}
