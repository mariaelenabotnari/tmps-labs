package edu.utm.tmps.Lab3.domain.command;

import edu.utm.tmps.Lab3.domain.model.Post;
import edu.utm.tmps.Lab3.domain.observer.User;

public class LikePostCommand implements ICommand {

    private final Post post;
    private final User user;
    private boolean executed = false;

    public LikePostCommand(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    @Override
    public void execute() {
        if (!executed) {
            boolean success = post.addLike(user);
            if (success) {
                executed = true;
            }
        }
    }

    @Override
    public void undo() {
        if (executed) {
            boolean success = post.removeLike(user);
            if (success) {
                executed = false;
            }
        }
    }

    @Override
    public boolean wasExecuted() {
        return executed;
    }

    @Override
    public String getDescription() {
        return user.getUsername() + " liked post " + post.getId();
    }
}
