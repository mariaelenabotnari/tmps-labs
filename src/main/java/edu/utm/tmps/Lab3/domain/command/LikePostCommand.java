package edu.utm.tmps.Lab3.domain.command;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.model.User;

public class LikePostCommand implements ICommand {

    private final Post post;
    private final User user;
    private final LikePostService likeService;

    private boolean executed = false;

    public LikePostCommand(Post post, User user, LikePostService likeService) {
        this.post = post;
        this.user = user;
        this.likeService = likeService;
    }

    @Override
    public void execute() {
        if (!executed && likeService.addLike(post, user)) {
            executed = true;
        }
    }

    @Override
    public void undo() {
        if (executed && likeService.removeLike(post, user)) {
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return user.getUsername() + " liked post " + post.getId();
    }

    public boolean wasExecuted() {
        return executed;
    }
}

