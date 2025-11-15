package edu.utm.tmps.Lab3.service;

import edu.utm.tmps.Lab3.model.User;

public interface IUserService {
    User register(String Username);
    User getUser(String id);
}
