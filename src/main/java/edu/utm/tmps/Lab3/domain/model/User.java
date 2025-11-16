package edu.utm.tmps.Lab3.domain.model;

import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;
import edu.utm.tmps.Lab3.domain.service.ProfileService;

public class User {
    private String id;
    private String username;
    private IProfilePictureService profileService;

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

    public IProfilePictureService getProfileService() {
        return profileService;
    }
}
