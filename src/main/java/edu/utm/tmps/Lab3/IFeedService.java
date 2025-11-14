package edu.utm.tmps.Lab3;

import java.util.ArrayList;

public interface IFeedService {
    ArrayList<Post> retrieveAllPosts();
    ArrayList<Post> retrievePostsUser(String userId);
    Post searchPost(String postId);
}
