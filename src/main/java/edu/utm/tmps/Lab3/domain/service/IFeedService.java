package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;
import edu.utm.tmps.Lab3.domain.strategy.IFeedSortingStrategy;

import java.util.ArrayList;

public interface IFeedService {
    ArrayList<Post> retrieveFeed(User currentUser);
    ArrayList<Post> retrievePostsUser(String userId);
    Post searchPost(String postId);
    void setSortingStrategy(IFeedSortingStrategy strategy);
}
