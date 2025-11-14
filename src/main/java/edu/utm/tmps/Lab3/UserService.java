package edu.utm.tmps.Lab3;

import java.util.HashMap;

public class UserService implements IUserService {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public User register(String username) {
        String id = "user-" + (users.size() + 1);
        User user = new User(id, username);
        users.put(id, user);
        System.out.println("UserService: registered " + username);
        return user;
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }
}
