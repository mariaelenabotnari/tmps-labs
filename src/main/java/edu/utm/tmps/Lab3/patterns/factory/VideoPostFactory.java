package edu.utm.tmps.Lab3.patterns.factory;

import edu.utm.tmps.Lab3.model.Post;
import edu.utm.tmps.Lab3.model.TextPost;

public class VideoPostFactory implements PostFactory {

    private static int counter = 0;

    @Override
    public Post createPost(String userId, String content) {
        String id = "post-vid-" + (++counter);
        return new TextPost(id, userId, content);
    }
}
