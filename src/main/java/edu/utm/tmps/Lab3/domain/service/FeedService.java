package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;
import edu.utm.tmps.Lab3.domain.service.IFeedService;
import edu.utm.tmps.Lab3.domain.service.IPostService;
import edu.utm.tmps.Lab3.domain.strategy.IFeedSortingStrategy;
import edu.utm.tmps.Lab3.domain.strategy.SortByNewestStrategy;

import java.util.ArrayList;

public class FeedService implements IFeedService {

    private final IPostService postService;
    private IFeedSortingStrategy sortingStrategy = new SortByNewestStrategy();

    public FeedService(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public ArrayList<Post> retrieveFeed(User currentUser) {
        ArrayList<Post> allPosts = postService.getPosts();
        return (ArrayList<Post>) sortingStrategy.sort(allPosts, currentUser);
    }

    @Override
    public ArrayList<Post> retrievePostsUser(String userId) {
        ArrayList<Post> userPosts = new ArrayList<>();

        for (Post p : postService.getPosts()) {
            if (p.getUserId().equals(userId)) {
                userPosts.add(p);
            }
        }

        return userPosts;
    }

    @Override
    public Post searchPost(String postId) {
        for (Post p : postService.getPosts()) {
            if (p.getId().equals(postId)) return p;
        }
        System.out.println("No such post.");
        return null;
    }

    @Override
    public void setSortingStrategy(IFeedSortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }
}
