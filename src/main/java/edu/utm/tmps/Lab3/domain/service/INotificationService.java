package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.observer.User;

public interface INotificationService {
    void sendNotification(User user, String notification);
}
