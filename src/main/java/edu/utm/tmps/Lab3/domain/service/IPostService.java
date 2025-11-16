package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.factory.PostFactory;
import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.proxy.CachedPostsProxy;

import java.util.ArrayList;

public interface IPostService {
    Post createPost(String userId, String content, PostFactory factory);
    ArrayList<Post> getPosts();
    public void setCacheProxy(CachedPostsProxy proxy);
}
