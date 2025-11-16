package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.model.ProfileInfo;

public class ProfileService implements IProfilePictureService, IProfileDetailsService {
    private ProfileInfo profile;

    public ProfileService(ProfileInfo profile) {
        this.profile = profile;
    }

    @Override
    public void addProfilePicture(String picture) {
        if (!isValidImage(picture)) {
            throw new IllegalArgumentException("Profile picture must be .png or .jpg/.jpeg");
        }
        profile.setProfilePicture(picture);
        profile.setAppliedFilterPicture("none");
        profile.setImageSizePicture("original");
        System.out.println(profile.getUsername() + " added profile picture " + picture);
    }

    @Override
    public void addBiography(String biography) {
        if (biography.length() < 5 || biography.length() > 150) {
            throw new IllegalArgumentException("Biography must be 5–150 characters.");
        }
        profile.setBiography(biography);
        System.out.println(profile.getUsername() + " added biography " + biography);
    }

    @Override
    public void addAge(Integer age) {
        if (age < 14 || age > 110) {
            throw new IllegalArgumentException("Age must be between 14 and 110.");
        }
        profile.setAge(age);
        System.out.println(profile.getUsername() + " added age " + age);
    }

    @Override
    public void addLocation(String location) {
        if (location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        profile.setLocation(location);
        System.out.println(profile.getUsername() + " added location " + location);
    }

    @Override
    public void displayProfile() {
        System.out.println("Username: " + profile.getUsername());
        System.out.println("Profile Picture: " + profile.getProfilePicture());
        System.out.println("Filter Applied to Profile Picture: " + profile.getAppliedFilterPicture());
        System.out.println("Size of the Profile Picture: " + profile.getImageSizePicture());
        System.out.println("Biography: " + profile.getBiography());
        System.out.println("Age: " + profile.getAge());
        System.out.println("Location: " + profile.getLocation());
    }

    @Override
    public void setImageSize(String size) {
        profile.setImageSizePicture(size);
    }

    @Override
    public void setAppliedFilter(String filter) {
        profile.setAppliedFilterPicture(filter);
    }

    private boolean isValidImage(String p) {
        return p.endsWith(".png") || p.endsWith(".jpg") || p.endsWith(".jpeg");
    }

}
