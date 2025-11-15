package edu.utm.tmps.Lab3.patterns.proxy;

import edu.utm.tmps.Lab3.model.Post;
import edu.utm.tmps.Lab3.service.IFeedService;

import java.util.ArrayList;
import java.util.HashMap;

public class CachedPostsProxy implements IFeedService {

    private final IFeedService feedService;

    private ArrayList<Post> cachedAll = null;
    private HashMap<String, ArrayList<Post>> cachedByUser = new HashMap<>();
    private HashMap<String, Post> cachedSearch = new HashMap<>();

    public CachedPostsProxy(IFeedService feedService) {
        this.feedService = feedService;
    }

    @Override
    public ArrayList<Post> retrieveAllPosts() {
        if (cachedAll == null) {
            System.out.println("Cache MISS: retrieving all posts...");
            cachedAll = feedService.retrieveAllPosts();
        } else {
            System.out.println("Cache HIT: retrieving all posts from cache.");
        }
        return cachedAll;
    }

    @Override
    public ArrayList<Post> retrievePostsUser(String userId) {
        if (!cachedByUser.containsKey(userId)) {
            System.out.println("Cache MISS: retrieving posts for user " + userId);
            cachedByUser.put(userId, feedService.retrievePostsUser(userId));
        } else {
            System.out.println("Cache HIT: retrieving posts for user " + userId);
        }
        return cachedByUser.get(userId);
    }

    @Override
    public Post searchPost(String postId) {
        if (!cachedSearch.containsKey(postId)) {
            System.out.println("Cache MISS: searching post " + postId);
            Post post = feedService.searchPost(postId);
            cachedSearch.put(postId, post);
        } else {
            System.out.println("Cache HIT: searching post " + postId + " from cache.");
        }
        return cachedSearch.get(postId);
    }

    public void clearCache() {
        cachedAll = null;
        cachedByUser.clear();
        cachedSearch.clear();
    }
}
