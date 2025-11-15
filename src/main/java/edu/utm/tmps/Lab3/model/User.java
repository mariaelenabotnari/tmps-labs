package edu.utm.tmps.Lab3.model;

import edu.utm.tmps.Lab3.service.IProfileService;
import edu.utm.tmps.Lab3.service.ProfileService;

public class User {
    private String id;
    private String username;
    private IProfileService profileService;

    public User(String id, String username, ProfileInfo profileInfo) {
        this.id = id;
        this.username = username;
        this.profileService = new ProfileService(profileInfo);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public IProfileService getProfileService() {
        return profileService;
    }
}
