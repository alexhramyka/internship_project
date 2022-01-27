package com.leverx.internship.project.user.repository;

import com.leverx.internship.project.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
