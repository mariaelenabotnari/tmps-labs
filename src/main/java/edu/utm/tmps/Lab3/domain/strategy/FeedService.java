package edu.utm.tmps.Lab3.domain.strategy;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.User;
import edu.utm.tmps.Lab3.domain.service.IFeedService;
import edu.utm.tmps.Lab3.domain.service.IPostService;

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

        for (Post post : postService.getPosts()) {
            if (post.getUserId().equals(userId)) {
                userPosts.add(post);
            }
        }

        return userPosts;
    }

    @Override
    public Post searchPost(String postId) {
        for (Post post : postService.getPosts()) {
            if (post.getId().equals(postId)) return post;
        }
        System.out.println("No such post.");
        return null;
    }

    @Override
    public void setSortingStrategy(IFeedSortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }
}
