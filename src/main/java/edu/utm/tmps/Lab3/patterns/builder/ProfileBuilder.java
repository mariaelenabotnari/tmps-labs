package edu.utm.tmps.Lab3.patterns.builder;

import edu.utm.tmps.Lab3.model.ProfileInfo;

public class ProfileBuilder {
    private String userId;
    private String username;
    private String profilePicture;

    public ProfileBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ProfileBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ProfileBuilder setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public ProfileInfo build() {
        return new ProfileInfo(userId, username, profilePicture);
    }
}
