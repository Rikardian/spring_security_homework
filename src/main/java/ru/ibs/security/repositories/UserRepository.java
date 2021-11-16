package ru.ibs.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibs.security.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityById(Integer id);
    UserEntity findUserEntityByUsername(String username);
    UserEntity deleteUserEntityById(Integer id);
}
