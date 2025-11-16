package edu.utm.tmps.Lab3.domain.model;

import java.time.LocalDateTime;

public abstract class Post {
    protected String id;
    protected String userId;
    protected String content;
    protected LocalDateTime createdAt;

    protected Post(String id, String userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public abstract void displayPost();

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
