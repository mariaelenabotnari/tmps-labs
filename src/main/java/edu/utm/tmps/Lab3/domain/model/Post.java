package edu.utm.tmps.Lab3.domain.model;

import edu.utm.tmps.Lab3.domain.observer.User;

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

    public boolean addLike(User user) {
        if (this.userId.equals(user.getId())) {
            System.out.println("You cannot like your own post.");
            return false;
        }

        if (likedUsers.contains(user.getId())) {
            System.out.println("You already liked this post.");
            return false;
        }
        likedUsers.add(user.getId());
        System.out.println(user.getUsername() + " liked this post.");
        return true;
    }

    public boolean removeLike(User user) {
        if (!likedUsers.contains(user.getId())) {
            return false;
        }
        likedUsers.remove(user.getId());
        System.out.println(user.getUsername() + " unliked this post.");
        return true;
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
}
