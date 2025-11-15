package edu.utm.tmps.Lab3.patterns.factory;

import edu.utm.tmps.Lab3.model.Post;

public interface PostFactory {
    Post createPost(String userId, String content);
}
