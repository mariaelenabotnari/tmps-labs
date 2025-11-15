package edu.utm.tmps.Lab3.patterns.factory;

import edu.utm.tmps.Lab3.model.ImagePost;
import edu.utm.tmps.Lab3.model.Post;

public class ImagePostFactory implements PostFactory {

    private static int counter = 0;

    @Override
    public Post createPost(String userId, String content) {
        String id = "post-img-" + (++counter);
        return new ImagePost(id, userId, content);
    }
}
