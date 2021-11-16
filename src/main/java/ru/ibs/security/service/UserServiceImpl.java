package ru.ibs.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibs.security.entities.UserEntity;
import ru.ibs.security.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(String username, String password, String role) {
        final UserEntity userEntity = new UserEntity(username, password, role);

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteUserEntityById(id);
    }

    @Override
    public UserEntity findUser(Integer id) {
        return userRepository.findUserEntityById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
