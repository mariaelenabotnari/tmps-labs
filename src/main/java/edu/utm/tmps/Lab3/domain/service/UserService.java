package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.builder.ProfileBuilder;
import edu.utm.tmps.Lab3.domain.model.ProfileInfo;
import edu.utm.tmps.Lab3.domain.observer.User;

import java.util.HashMap;

public class UserService implements IUserService {
    private HashMap<String, User> users = new HashMap<>();
    private final HashMap<String, User> usersByUsername = new HashMap<>();

    @Override
    public User register(String username) {
        if (username.length() < 4 || username.length() > 30) {
            throw new IllegalArgumentException("Username must be 4-30 characters.");
        }

        if (usersByUsername.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }

        String id = "user-" + (users.size() + 1);

        ProfileInfo profileInfo = new ProfileBuilder()
                .setUserId(id)
                .setUsername(username)
                .setProfilePicture("default.png")
                .build();

        User user = new User(id, username, profileInfo);
        users.put(id, user);
        usersByUsername.put(username, user);
        System.out.println("UserService: registered " + username);
        return user;
    }

    public User login(String username) {
        return usersByUsername.get(username);
    }

    @Override
    public User getUserById(String id) {
        return users.get(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return usersByUsername.get(username);
    }
}
