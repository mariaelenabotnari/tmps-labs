package edu.utm.tmps.Lab3;

import java.util.ArrayList;

public class PostService implements IPostService {

    private ArrayList<Post> posts = new ArrayList<>();

    @Override
    public Post createPost(String userId, String content) {
        Post post = new Post("post-" + (posts.size() + 1), userId, content);
        posts.add(post);
        System.out.println("PostService: created post for " + userId);
        return post;

    }

    @Override
    public Post updatePost(String postId, String content) {
        int i = 0;
        for (Post post : posts) {
            if (("post-" + postId).equals(post.getId())) {
                post.setContent(content);
            }
            i += 1;
        }
        return posts.get(i);
    }

    @Override
    public void deletePost(String postId) {
        int i = 0;
        for (Post post : posts) {
            if (("post-" + postId).equals(post.getId())) {
                posts.remove(i);
            }
            i += 1;
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

}
