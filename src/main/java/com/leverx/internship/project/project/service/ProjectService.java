package com.leverx.internship.project.project.service;

import com.leverx.internship.project.project.web.dto.ProjectDto;

import java.util.List;
import java.util.Map;

public interface ProjectService {
  List<ProjectDto> findAll(int page, int size);

  ProjectDto findById(int id);

  String create(ProjectDto projectDto);

  String update(int id, Map<String, Object> values);

  String delete(int id);
}
