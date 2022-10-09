package ru.springBoot311.SpringBoot.sources.service;

import ru.springBoot311.SpringBoot.sources.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> getAllUsers();

    public User getUser(long id);

    public void updateUser(User user);
    public void deleteUser(long id);
}

