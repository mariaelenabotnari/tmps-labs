package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.patterns.factory.PostFactory;
import edu.utm.tmps.Lab3.model.Post;
import edu.utm.tmps.Lab3.patterns.proxy.CachedPostsProxy;

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

    @Override
    public Post updatePost(String postId, String content) {
        for (Post post : posts) {
            if (post.getId().equals(postId)) {
                post.setContent(content);

                if (cacheProxy != null) {
                    cacheProxy.clearCache();
                }

                return post;
            }
        }
        return null;
    }

    @Override
    public void deletePost(String postId) {
        posts.removeIf(post -> post.getId().equals(postId));

        if (cacheProxy != null) {
            cacheProxy.clearCache();
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
