package edu.utm.tmps.Lab3.domain.strategy;
import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;

import java.util.Comparator;
import java.util.List;

public class SortByNewestStrategy implements IFeedSortingStrategy{

    @Override
    public List<Post> sort(List<Post> posts, User currentUser) {
        posts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return posts;
    }
}
