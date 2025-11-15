package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.patterns.builder.ProfileBuilder;
import edu.utm.tmps.Lab3.model.ProfileInfo;
import edu.utm.tmps.Lab3.model.User;

import java.util.HashMap;

public class UserService implements IUserService {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public User register(String username) {
        String id = "user-" + (users.size() + 1);

        ProfileInfo profileInfo = new ProfileBuilder()
                .setUserId(id)
                .setUsername(username)
                .setProfilePicture("default.png")
                .build();

        User user = new User(id, username, profileInfo);
        users.put(id, user);
        System.out.println("UserService: registered " + username);
        return user;
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }
}
