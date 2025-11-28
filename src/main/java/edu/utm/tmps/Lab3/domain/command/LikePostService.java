package edu.utm.tmps.Lab3.domain.command;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.User;

import java.util.Set;

public class LikePostService {

    public boolean addLike(Post post, User user) {

        if (post.getUserId().equals(user.getId())) {
            System.out.println("You cannot like your own post.");
            return false;
        }

        Set<String> likedUsers = post.getLikedUsers();
        if (likedUsers.contains(user.getId())) {
            System.out.println("You already liked this post.");
            return false;
        }

        likedUsers.add(user.getId());
        System.out.println(user.getUsername() + " liked this post.");
        return true;
    }

    public boolean removeLike(Post post, User user) {
        Set<String> likedUsers = post.getLikedUsers();

        if (!likedUsers.contains(user.getId())) {
            return false;
        }

        likedUsers.remove(user.getId());
        System.out.println(user.getUsername() + " unliked this post.");
        return true;
    }
}

