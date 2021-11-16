package ru.ibs.security.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ibs.security.config.ApplicationUserRole;
import ru.ibs.security.entities.UserEntity;
import ru.ibs.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.ibs.security.config.ApplicationUserRole.*;

@RequiredArgsConstructor
@Service("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        userService.createUser("oliver", "password", "EMPLOYEE");
        userService.createUser("henry", "password123", "MANAGER");
        userService.createUser("emma", "password123", "TRAINEE");
        userService.createUser("Boris", "password", "SCRUM_MASTER");
    }

    @Override
    public Optional<ApplicationUser> selectUserFromDbByUserName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {

        List<UserEntity> userEntities = userService.findAll();

        List<ApplicationUser> applicationUsers = userEntities
                .stream()
                .map(x -> new ApplicationUser(
                        x.getUsername(),
                        passwordEncoder.encode(x.getPassword()),
                        ApplicationUserRole.valueOf(x.getRole()).getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ))
                .collect(Collectors.toList());

        return applicationUsers;
    }
}
