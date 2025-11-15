package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.patterns.factory.PostFactory;
import edu.utm.tmps.Lab3.model.Post;
import edu.utm.tmps.Lab3.patterns.proxy.CachedPostsProxy;

import java.util.ArrayList;

public interface IPostService {
    Post createPost(String userId, String content, PostFactory factory);
    Post updatePost(String postId, String content);
    void deletePost(String postId);
    ArrayList<Post> getPosts();
    public void setCacheProxy(CachedPostsProxy proxy);
}
