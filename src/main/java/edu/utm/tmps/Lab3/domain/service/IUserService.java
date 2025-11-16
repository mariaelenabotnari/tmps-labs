package edu.utm.tmps.Lab3.domain.service;

import edu.utm.tmps.Lab3.domain.model.User;

public interface IUserService {
    User register(String Username);
    User getUser(String id);
}
