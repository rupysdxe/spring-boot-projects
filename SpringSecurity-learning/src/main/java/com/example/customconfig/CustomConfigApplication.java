package com.example.customconfig;

import com.example.customconfig.auth.entity.Role;
import com.example.customconfig.auth.entity.User;
import com.example.customconfig.auth.enums.ERole;
import com.example.customconfig.auth.repo.RoleRepository;
import com.example.customconfig.auth.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomConfigApplication {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CustomConfigApplication.class, args);
    }

    @PostConstruct
    public void init(){
        Role roleUser = new Role();
        roleUser.setRole(ERole.ROLE_USER);
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setRole(ERole.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleAdmin);
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(roles);
        userRepository.save(user);
    }

}
