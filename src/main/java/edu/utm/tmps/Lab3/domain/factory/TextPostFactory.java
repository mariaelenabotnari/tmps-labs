package edu.utm.tmps.Lab3.domain.factory;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.TextPost;

public class TextPostFactory implements PostFactory {

    private static int counter = 0;

    @Override
    public Post createPost(String userId, String content) {
        if (content.isEmpty() || content.length() > 300) {
            throw new IllegalArgumentException("Text post content must be 1–300 characters.");
        }
        String id = "post-text-" + (++counter);
        return new TextPost(id, userId, content);
    }
}
