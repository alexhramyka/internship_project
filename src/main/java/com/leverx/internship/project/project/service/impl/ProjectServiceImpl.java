package com.leverx.internship.project.project.service.impl;

import com.leverx.internship.project.project.repository.ProjectRepository;
import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.service.ProjectService;
import com.leverx.internship.project.project.web.converter.ProjectConverter;
import com.leverx.internship.project.project.web.dto.ProjectDto;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.converter.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectConverter projectConverter;
  private final UserService userService;
  private final UserConverter userConverter;

  @Transactional(readOnly = true)
  @Override
  public List<ProjectDto> findAll(int page, int size) {
    Pageable paging = PageRequest.of(page, size);
    Page<Project> projectPage = projectRepository.findAll(paging);
    List<ProjectDto> projectsDto = new ArrayList<>();
    projectPage.forEach(project -> projectsDto.add(projectConverter.toProjectDto(project)));
    return projectsDto;
  }

  @Transactional(readOnly = true)
  @Override
  public ProjectDto findById(int id) {
    Project project = getProject(id);
    return projectConverter.toProjectDto(project);
  }

  @Transactional
  @Override
  public ProjectDto create(ProjectDto projectDto) {
    Project project = projectConverter.toEntity(projectDto);
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());
    return projectConverter.toProjectDto(projectRepository.save(project));
  }

  @Transactional
  @Override
  public ProjectDto update(int id, ProjectDto projectDtoToUpdate) {
    Project project = getProject(id);
    Project newProject = projectConverter.toEntity(
        projectConverter.toUpdatedProjectDto(projectDtoToUpdate, project));
    newProject.setCreatedAt(project.getCreatedAt());
    newProject.setUpdatedAt(LocalDate.now());
    newProject.setId(project.getId());
    return projectConverter.toProjectDto(projectRepository.save(newProject));
  }

  @Transactional
  @Override
  public void delete(int id) {
    projectRepository.deleteById(id);
  }

  @Transactional
  @Override
  public ProjectDto addUserToProject(int idProject, int idUser) {
    User user = userConverter.toEntity(userService.findById(idUser));
    user.setId(idUser);
    Project project = getProject(idProject);
    project.addEmployee(user);
    projectRepository.save(project);
    return projectConverter.toProjectDto(project);
  }

  @Transactional
  @Override
  public void deleteUserFromProject(int idProject, int idUser) {
    User user = userConverter.toEntity(userService.findById(idUser));
    user.setId(idUser);
    Project project = getProject(idProject);
    if (project.getEmployees().contains(user)) {
      project.removeEmployee(user);
      projectRepository.save(project);
    } else throw new NotFoundException("User with id " + idUser + " doesn't  participate in the project with id " + idProject);
  }

  private Project getProject(int id) {
    return projectRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Project with id: " + id + " doesn't exist"));
  }
}