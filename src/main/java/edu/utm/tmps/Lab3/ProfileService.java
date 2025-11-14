package edu.utm.tmps.Lab3;

import java.util.HashMap;

public class ProfileService implements IProfileService {
    private ProfileInfo profile;

    public ProfileService(ProfileInfo profile) {
        this.profile = profile;
    }

    @Override
    public void addProfilePicture(String picture) {
        profile.profilePicture = picture;
        System.out.println(profile.username + " added profile picture " + picture);
    }

    @Override
    public void displayProfile() {
        System.out.println("Username: " + profile.userId);
        System.out.println("Profile Picture: " + profile.profilePicture);
    }

}
