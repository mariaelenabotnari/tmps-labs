package edu.utm.tmps.Lab3.domain.strategy;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;

import java.util.List;

public interface IFeedSortingStrategy {
    List<Post> sort(List<Post> posts, User currentUser);
}