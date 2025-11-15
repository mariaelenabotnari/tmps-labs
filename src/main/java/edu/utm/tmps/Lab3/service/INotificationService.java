package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.model.User;

public interface INotificationService {
    void sendNotification(User user, String notification);
}
