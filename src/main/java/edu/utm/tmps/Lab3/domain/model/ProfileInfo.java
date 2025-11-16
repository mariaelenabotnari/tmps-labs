package edu.utm.tmps.Lab3.domain.model;

public class ProfileInfo {
    private String userId;
    private String username;
    private String profilePicture;
    private String appliedFilterPicture = "none";
    private String imageSizePicture = "original";
    private String biography;
    private Integer age;
    private String location;

    public ProfileInfo(String userId, String username, String profilePicture) {
        this.userId = userId;
        this.username = username;
        this.profilePicture = profilePicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAppliedFilterPicture() {
        return appliedFilterPicture;
    }

    public void setAppliedFilterPicture(String appliedFilterPicture) {
        this.appliedFilterPicture = appliedFilterPicture;
    }

    public String getImageSizePicture() {
        return imageSizePicture;
    }

    public void setImageSizePicture(String imageSizePicture) {
        this.imageSizePicture = imageSizePicture;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
