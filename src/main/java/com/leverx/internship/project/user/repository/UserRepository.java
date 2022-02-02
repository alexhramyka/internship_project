package com.leverx.internship.project.user.repository;

import com.leverx.internship.project.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
    JpaSpecificationExecutor<User> {
}
