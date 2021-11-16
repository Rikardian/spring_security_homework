package ru.ibs.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String role;

    public UserEntity (String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String toString() {
        return String.join(" ", username, password, role);
    }

}