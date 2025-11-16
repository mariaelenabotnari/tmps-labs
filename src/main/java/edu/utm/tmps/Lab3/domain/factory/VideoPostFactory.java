package edu.utm.tmps.Lab3.domain.factory;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.VideoPost;

public class VideoPostFactory implements PostFactory {

    private static int counter = 0;

    @Override
    public Post createPost(String userId, String content) {
        if (!isValidPath(content)) {
            throw new IllegalArgumentException("Invalid video path (.mp4/.mov/.avi required).");
        }
        String id = "post-vid-" + (++counter);
        return new VideoPost(id, userId, content);
    }

    private boolean isValidPath(String c) {
        return c.endsWith(".mp4") || c.endsWith(".mov") || c.endsWith(".avi");
    }
}
