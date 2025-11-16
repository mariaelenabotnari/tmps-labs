package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.factory.PostFactory;
import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.proxy.CachedPostsProxy;

import java.util.ArrayList;

public class PostService implements IPostService {

    private ArrayList<Post> posts = new ArrayList<>();
    private CachedPostsProxy cacheProxy;

    public void setCacheProxy(CachedPostsProxy proxy) {
        this.cacheProxy = proxy;
    }

    @Override
    public Post createPost(String userId, String content, PostFactory factory) {
        Post post = factory.createPost(userId, content);
        posts.add(post);
        System.out.println("PostService: created post for " + userId);

        if (cacheProxy != null) {
            cacheProxy.clearCache();
        }

        return post;

    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
