package edu.utm.tmps.Lab3.domain.factory;

import edu.utm.tmps.Lab3.domain.model.Post;

public interface PostFactory {
    Post createPost(String userId, String content);
}
