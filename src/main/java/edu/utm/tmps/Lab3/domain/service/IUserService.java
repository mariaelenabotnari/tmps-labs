package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.observer.User;

public interface IUserService {
    User register(String Username);
    public User login(String username);
    public User getUserByUsername(String username);
    User getUserById(String id);
}
