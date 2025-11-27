package edu.utm.tmps.Lab3.domain.observer;

import edu.utm.tmps.Lab3.domain.model.ProfileInfo;
import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;
import edu.utm.tmps.Lab3.domain.service.ProfileService;

import java.util.ArrayList;
import java.util.List;

public class User implements IObserver, ISubject {
    private String id;
    private String username;
    private IProfilePictureService profileService;

    private final List<IObserver> followers = new ArrayList<>();
    private final List<User> following = new ArrayList<>();

    public User(String id, String username, ProfileInfo profileInfo) {
        this.id = id;
        this.username = username;
        this.profileService = new ProfileService(profileInfo);
    }

    @Override
    public void update(String message) {
        System.out.println(username + " received -> " + message);
    }

    @Override
    public void registerObserver(IObserver observer) {
        followers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        followers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (IObserver o : followers) {
            o.update(message);
        }
    }

    public void follow(User userToFollow) {
        userToFollow.registerObserver(this);
        following.add(userToFollow);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public IProfilePictureService getProfileService() {
        return profileService;
    }

    public List<User> getFollowing() {
        return following;
    }
}
