package edu.utm.tmps.Lab3.domain.strategy;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;

import java.util.List;
import java.util.stream.Collectors;

public class SortByFollowingOnlyStrategy implements IFeedSortingStrategy{

    @Override
    public List<Post> sort(List<Post> posts, User currentUser) {
        List<String> followingIds = currentUser.getFollowing()
                .stream()
                .map(User::getId)
                .toList();

        return posts.stream()
                .filter(p -> followingIds.contains(p.getUserId()))
                .collect(Collectors.toList());
    }
}
