package edu.utm.tmps.Lab3.domain.observer;

import edu.utm.tmps.Lab3.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class FollowManager implements ISubject, IObserver {

    private final User user;
    private final List<FollowManager> followers = new ArrayList<>();
    private final List<FollowManager> following = new ArrayList<>();

    public FollowManager(User user) {
        this.user = user;
    }

    @Override
    public void update(String msg) {
        System.out.println(user.getUsername() + " received -> " + msg);
    }

    @Override
    public void registerObserver(IObserver observer) {
        followers.add((FollowManager) observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        followers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        for (FollowManager f : followers) {
            f.update(msg);
        }
    }

    public void follow(FollowManager target) {
        target.registerObserver(this);
        following.add(target);
    }

    public void unfollow(FollowManager target) {
        target.removeObserver(this);
        following.remove(target);
    }

    public List<FollowManager> getFollowing() {
        return following;
    }

    public User getUser() {
        return user;
    }
}
