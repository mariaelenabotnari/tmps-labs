package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.model.ProfileInfo;

public class ProfileService implements IProfileService {
    private ProfileInfo profile;

    public ProfileService(ProfileInfo profile) {
        this.profile = profile;
    }

    @Override
    public void addProfilePicture(String picture) {
        profile.setProfilePicture(picture);
        System.out.println(profile.getUsername() + " added profile picture " + picture);
    }

    @Override
    public void displayProfile() {
        System.out.println("Username: " + profile.getUsername());
        System.out.println("Profile Picture: " + profile.getProfilePicture());
    }

}
