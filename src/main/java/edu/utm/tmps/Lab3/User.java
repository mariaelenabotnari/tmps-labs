package edu.utm.tmps.Lab3;

public class User {
    private String id;
    private String username;
    private ProfileInfo profileInfo;
    private IProfileService profileService;

    public User(String id, String username) {
        this.id = id;
        this.username = username;

        profileInfo = new ProfileInfo();
        profileInfo.userId = id;
        profileInfo.username = username;
        profileService = new ProfileService(profileInfo);
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
