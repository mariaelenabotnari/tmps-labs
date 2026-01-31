package edu.utm.tmps.Lab3.domain.model;

import edu.utm.tmps.Lab3.domain.observer.FollowManager;
import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;
import edu.utm.tmps.Lab3.domain.service.ProfileService;

public class User {
    private String id;
    private String username;
    private IProfilePictureService profileService;
    private final FollowManager followManager;

    public User(String id, String username, ProfileInfo profileInfo) {
        this.id = id;
        this.username = username;
        this.profileService = new ProfileService(profileInfo);
        this.followManager = new FollowManager(this);
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

    public FollowManager getFollowManager() {
        return followManager;
    }
}
