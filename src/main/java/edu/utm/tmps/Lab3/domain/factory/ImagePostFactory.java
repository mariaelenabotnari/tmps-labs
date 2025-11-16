package edu.utm.tmps.Lab3.domain.factory;

import edu.utm.tmps.Lab3.domain.model.ImagePost;
import edu.utm.tmps.Lab3.domain.model.Post;

public class ImagePostFactory implements PostFactory {

    private static int counter = 0;

    @Override
    public Post createPost(String userId, String content) {
        if (!isValidPath(content)) {
            throw new IllegalArgumentException("Invalid image path (.png/.jpg/.jpeg required).");
        }
        String id = "post-img-" + (++counter);
        return new ImagePost(id, userId, content);
    }

    private boolean isValidPath(String c) {
        return c.endsWith(".png") || c.endsWith(".jpg") || c.endsWith(".jpeg");
    }
}
