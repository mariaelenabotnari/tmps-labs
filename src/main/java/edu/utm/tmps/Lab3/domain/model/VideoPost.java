package edu.utm.tmps.Lab3.domain.model;

public class VideoPost extends Post {
    public VideoPost(String id, String userId, String content) {
        super(id, userId, content);
    }

    @Override
    public void displayPost() {
        System.out.println("=== YOUR VIDEO POST ===");
        System.out.println("User: " + userId);
        System.out.println("Video: " + content);
        System.out.println("Created at: " + createdAt);
    }
}
