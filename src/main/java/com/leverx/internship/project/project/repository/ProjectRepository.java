package com.leverx.internship.project.project.repository;

import com.leverx.internship.project.project.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository extends JpaRepository<Project, Integer>,
    JpaSpecificationExecutor<Project> {
}
