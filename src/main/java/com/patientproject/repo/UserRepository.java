package com.patientproject.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientproject.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByEmail(String email);

}
