package ru.ibs.security.service;

import ru.ibs.security.entities.UserEntity;

import java.util.List;

public interface UserService {

    void createUser(String username, String password, String role);

    void deleteUser(Integer id);

    UserEntity findUser(Integer id);

    List<UserEntity> findAll();
}
