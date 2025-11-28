package edu.utm.tmps.Lab3.domain.strategy;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.FollowManager;
import edu.utm.tmps.Lab3.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class SortByFollowingOnlyStrategy implements IFeedSortingStrategy{

    @Override
    public List<Post> sort(List<Post> posts, User currentUser) {

        List<FollowManager> followingList = currentUser.getFollowManager().getFollowing();
        List<String> followingIds = new ArrayList<>();

        for (FollowManager follower : followingList) {
            followingIds.add(follower.getUser().getId());
        }

        List<Post> result = new ArrayList<>();

        for (Post post : posts) {
            if (followingIds.contains(post.getUserId())) {
                result.add(post);
            }
        }

        return result;
    }

}
