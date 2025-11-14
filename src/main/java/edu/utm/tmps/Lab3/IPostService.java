package edu.utm.tmps.Lab3;

import java.util.ArrayList;

public interface IPostService {
    Post createPost(String userId, String content);
    Post updatePost(String postId, String content);
    void deletePost(String postId);
    ArrayList<Post> getPosts();
}
