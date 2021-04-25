package com.brainfluence.psychiatry.model;

public class NotificationModel {
    public String notificationTitle,notificationBody,notificationDate;

    public NotificationModel() {
        this.notificationTitle = null;
        this.notificationBody = null;
        this.notificationDate = null;
    }

    public NotificationModel(String notificationTitle, String notificationBody, String notificationDate) {
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.notificationDate = notificationDate;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }
}
