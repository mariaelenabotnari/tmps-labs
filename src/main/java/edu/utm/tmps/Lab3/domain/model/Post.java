package edu.utm.tmps.Lab3.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Post {
    protected String id;
    protected String userId;
    protected String content;
    protected LocalDateTime createdAt;
    private final Set<String> likedUsers = new HashSet<>();

    protected Post(String id, String userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public int getLikes() {
        return likedUsers.size();
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

    public Set<String> getLikedUsers() {
        return likedUsers;
    }
}
