package com.leverx.internship.project.project.service;

import com.leverx.internship.project.project.web.dto.ProjectDto;
import java.util.List;

public interface ProjectService {
  List<ProjectDto> findAll(int page, int size);

  ProjectDto findById(int id);

  ProjectDto create(ProjectDto projectDto);

  ProjectDto update(int id, ProjectDto projectDtoToUpdate);

  void delete(int id);

  ProjectDto addUserToProject(int idProject, int idUser);

  void deleteUserFromProject(int idProject, int idUser);
}
