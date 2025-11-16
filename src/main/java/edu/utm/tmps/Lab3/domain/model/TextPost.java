package edu.utm.tmps.Lab3.domain.model;

public class TextPost extends Post {

    public TextPost(String id, String userId, String content) {
        super(id, userId, content);
    }

    @Override
    public void displayPost() {
        System.out.println("=== YOUR TEXT POST ===");
        System.out.println("User: " + userId);
        System.out.println("Text: " + content);
        System.out.println("Created at: " + createdAt);
    }
}
