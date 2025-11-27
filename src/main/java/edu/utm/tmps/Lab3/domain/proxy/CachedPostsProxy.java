package edu.utm.tmps.Lab3.domain.proxy;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;
import edu.utm.tmps.Lab3.domain.service.IFeedService;
import edu.utm.tmps.Lab3.domain.strategy.IFeedSortingStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class CachedPostsProxy implements IFeedService {

    private final IFeedService feed;

    private ArrayList<Post> cachedFeed = null;
    private HashMap<String, ArrayList<Post>> cachedUser = new HashMap<>();
    private HashMap<String, Post> cachedSearch = new HashMap<>();

    public CachedPostsProxy(IFeedService feed) { this.feed = feed; }

    @Override
    public ArrayList<Post> retrieveFeed(User currentUser) {
        clearCache();

        if (cachedFeed == null) {
            System.out.println("Cache MISS: FEED");
            cachedFeed = feed.retrieveFeed(currentUser);
        } else {
            System.out.println("Cache HIT: FEED");
        }
        return cachedFeed;
    }

    @Override
    public ArrayList<Post> retrievePostsUser(String userId) {
        if (!cachedUser.containsKey(userId)) {
            System.out.println("Cache MISS: user " + userId);
            cachedUser.put(userId, feed.retrievePostsUser(userId));
        } else {
            System.out.println("Cache HIT: user " + userId);
        }
        return cachedUser.get(userId);
    }

    @Override
    public Post searchPost(String postId) {
        if (!cachedSearch.containsKey(postId)) {
            System.out.println("Cache MISS: post " + postId);
            cachedSearch.put(postId, feed.searchPost(postId));
        } else {
            System.out.println("Cache HIT: post " + postId);
        }
        return cachedSearch.get(postId);
    }

    @Override
    public void setSortingStrategy(IFeedSortingStrategy strategy) {
        clearCache();
        feed.setSortingStrategy(strategy);
    }

    public void clearCache() {
        cachedFeed = null;
        cachedUser.clear();
        cachedSearch.clear();
    }
}

