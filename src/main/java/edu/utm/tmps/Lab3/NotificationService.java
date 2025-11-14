package edu.utm.tmps.Lab3;

public class NotificationService implements INotificationService {
    @Override
    public void sendNotification(User user, String notification) {
        System.out.println(user.getUsername() + " got this notification: " + notification);
    }
}
