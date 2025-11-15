package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.model.User;

public class NotificationService implements INotificationService {
    @Override
    public void sendNotification(User user, String notification) {
        System.out.println(user.getUsername() + " got this notification: " + notification);
    }
}
