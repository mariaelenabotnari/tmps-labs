package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.model.Post;

import java.util.ArrayList;

public class FeedService implements IFeedService {

    private final IPostService postService;

    public FeedService(IPostService postService) {
        this.postService = postService;
    }

    @Override
    public ArrayList<Post> retrieveAllPosts() {
        if (postService.getPosts().isEmpty()) {
            System.out.println("No posts found");
        }

        return postService.getPosts();
    }

    @Override
    public ArrayList<Post> retrievePostsUser(String userId) {
        ArrayList<Post> userPosts = new ArrayList<>();
        for (Post post : postService.getPosts()) {
            if (post.getUserId().equals(userId)) {
                userPosts.add(post);
            }
        }

        if (userPosts.isEmpty()) {
            System.out.println("No posts found");
        }

        return userPosts;
    }

    @Override
    public Post searchPost(String postId) {
        for (Post post : postService.getPosts()) {
            if (post.getId().equals(postId)) {
                return post;
            }
        }
        System.out.println("No such post.");
        return null;
    }
}
