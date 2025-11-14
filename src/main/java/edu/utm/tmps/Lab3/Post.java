package edu.utm.tmps.Lab3;

import java.time.LocalDateTime;

public class Post {
    private String id;
    private String userId;
    private String content;
    private LocalDateTime createdAt;

    public Post(String id, String userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
