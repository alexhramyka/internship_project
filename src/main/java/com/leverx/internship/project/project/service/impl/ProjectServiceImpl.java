package com.leverx.internship.project.project.service.impl;

import com.leverx.internship.project.project.repository.ProjectRepository;
import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.service.ProjectService;
import com.leverx.internship.project.project.service.filter.ProjectSpecification;
import com.leverx.internship.project.project.web.converter.ProjectConverter;
import com.leverx.internship.project.project.web.dto.request.ProjectBodyRequest;
import com.leverx.internship.project.project.web.dto.request.ProjectParamRequest;
import com.leverx.internship.project.project.web.dto.response.ProjectResponse;
import com.leverx.internship.project.project.web.dto.response.ProjectUsersResponse;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectConverter projectConverter;
  private final UserService userService;
  private final UserConverter userConverter;

  @Transactional(readOnly = true)
  @Override
  public List<ProjectResponse> findAll(int page, int size, ProjectParamRequest params) {
    Pageable paging = PageRequest.of(page, size);
    Specification<Project> spec =
        Specification.where(
            ProjectSpecification.projectParamHasDescription(params.getDescription())
                .and(ProjectSpecification.projectParamHasName(params.getName())));
    Page<Project> projectPage = projectRepository.findAll(spec, paging);
    List<ProjectResponse> projectsDto = new ArrayList<>();
    projectPage.forEach(project -> projectsDto.add(projectConverter.toProjectResponse(project)));
    return projectsDto;
  }

  @Transactional(readOnly = true)
  @Override
  public ProjectResponse findById(Integer id) {
    Project project = getProject(id);
    return projectConverter.toProjectResponse(project);
  }

  @Transactional
  @Override
  public ProjectResponse create(ProjectBodyRequest projectBodyRequest) {
    Project project = projectConverter.toEntity(projectBodyRequest);
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());
    return projectConverter.toProjectResponse(projectRepository.save(project));
  }

  @Transactional
  @Override
  public ProjectResponse update(Integer id, ProjectBodyRequest projectBodyRequestToUpdate) {
    Project project = getProject(id);
    Project newProject =
        projectConverter.toEntity(
            projectConverter.toUpdatedProjectResponse(projectBodyRequestToUpdate, project));
    newProject.setCreatedAt(project.getCreatedAt());
    newProject.setUpdatedAt(LocalDate.now());
    newProject.setId(project.getId());
    return projectConverter.toProjectResponse(projectRepository.save(newProject));
  }

  @Transactional
  @Override
  public void delete(Integer id) {
    projectRepository.deleteById(id);
  }

  @Transactional
  @Override
  public ProjectUsersResponse findProjectUsers(Integer id) {
    return projectConverter.toProjectUsersResponse(getProject(id));
  }

  @Transactional
  @Override
  public ProjectUsersResponse addUserToProject(Integer idProject, Integer idUser) {
    UserResponse user = userService.findById(idUser);
    user.setId(idUser);
    Project project = getProject(idProject);
    ProjectUsersResponse projectUsersResponse = projectConverter.toProjectUsersResponse(project);
    if (!projectUsersResponse.getUsers().contains(user)) {
      projectUsersResponse.addUser(user);
      project.setEmployees(userConverter.usersResponseSetToUserSet(projectUsersResponse.getUsers()));
      projectRepository.save(project);
      return projectConverter.toProjectUsersResponse(project);
    } else {
      throw new DataIntegrityViolationException(
          "User with id " + idUser + " already on this project");
    }
  }

  @Transactional
  @Override
  public void deleteUserFromProject(Integer idProject, Integer idUser) {
    UserResponse user = userService.findById(idUser);
    user.setId(idUser);
    Project project = getProject(idProject);
    ProjectUsersResponse projectUsersResponse = projectConverter.toProjectUsersResponse(project);
    if (projectUsersResponse.getUsers().contains(user)) {
      projectUsersResponse.removeUser(user);
      project.setEmployees(userConverter.usersResponseSetToUserSet(projectUsersResponse.getUsers()));
      projectRepository.save(project);
    } else {
      throw new NotFoundException(
          "User with id " + idUser + " doesn't  participate in the project with id " + idProject);
    }
  }

  private Project getProject(int id) {
    return projectRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Project with id: " + id + " doesn't exist"));
  }
}