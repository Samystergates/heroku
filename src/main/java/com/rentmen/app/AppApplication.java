package com.rentmen.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.rentmen.app.entities.Role;
import com.rentmen.app.entities.User;
import com.rentmen.app.repositories.RoleRepo;
import com.rentmen.app.repositories.UserRepo;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner init(RoleRepo roleRepository, UserRepo userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            initRoles(roleRepository);
            initAdminUser(userRepository, roleRepository, passwordEncoder);
        };
    }

    @Transactional
    private void initRoles(RoleRepo roleRepository) {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role normalRole = new Role();
            normalRole.setName("ROLE_NORMAL");
            roleRepository.save(normalRole);
        }
    }
    @Transactional
    private void initAdminUser(UserRepo userRepository, RoleRepo roleRepository, BCryptPasswordEncoder passwordEncoder) {
        if (!userRepository.existsByName("admin")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
            User adminUser = new User();
            adminUser.setName("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.getRoles().add(adminRole);
            adminUser.setActive(true);
            adminUser.setDepId(1);
            adminUser.setAbout("admin");
            userRepository.save(adminUser);
        }
    }
}
