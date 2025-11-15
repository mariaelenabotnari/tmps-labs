package edu.utm.tmps.Lab3.model;

public class ImagePost extends Post {

    public ImagePost(String id, String userId, String content) {
        super(id, userId, content);
    }

    @Override
    public void displayPost() {
        System.out.println("=== YOUR IMAGE POST ===");
        System.out.println("User: " + userId);
        System.out.println("Image: " + content);
        System.out.println("Created at: " + createdAt);
    }
}
