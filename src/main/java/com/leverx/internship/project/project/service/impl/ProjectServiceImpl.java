package com.leverx.internship.project.project.service.impl;

import com.leverx.internship.project.project.repository.ProjectRepository;
import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.service.ProjectService;
import com.leverx.internship.project.project.web.converter.ProjectConverter;
import com.leverx.internship.project.project.web.dto.ProjectDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectConverter projectConverter;

  @Transactional
  @Override
  public List<ProjectDto> findAll(int page, int size) {
    Pageable paging = PageRequest.of(page, size);
    Page<Project> projectPage = projectRepository.findAll(paging);
    List<ProjectDto> projectsDto = new ArrayList<>();
    projectPage.forEach(project -> projectsDto.add(projectConverter.toProjectDto(project)));
    return projectsDto;
  }

  @Transactional
  @Override
  public ProjectDto findById(int id) {
    Project project = getProject(id);
    return projectConverter.toProjectDto(project);
  }

  @Transactional
  @Override
  public String create(ProjectDto projectDto) {
    Project project = projectConverter.toEntity(projectDto);
    return projectRepository.save(project).toString();
  }

  @Transactional
  @Override
  public String update(int id, Map<String, Object> values) {
    Project project = getProject(id);
    Project newProject = projectConverter.toEntity(projectConverter.toUpdatedProjectDto(values, project));
    return projectRepository.save(newProject).toString();
  }

  @Transactional
  @Override
  public String delete(int id) {
    projectRepository.deleteById(id);
    return "Project deleted successfully";
  }

  private Project getProject(int id) {
    return projectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project with id: " + id + " doesn't exist"));
  }
}