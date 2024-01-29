package com.rentmen.app.repositories;

import com.rentmen.app.entities.Role;
import com.rentmen.app.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	boolean existsByName(String name);
	Optional<Role> findByName(String paramString);
}
