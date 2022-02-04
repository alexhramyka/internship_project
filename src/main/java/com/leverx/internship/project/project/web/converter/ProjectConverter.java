package com.leverx.internship.project.project.web.converter;

import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.web.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class ProjectConverter {
  private final ModelMapper mapper = new ModelMapper();

  public Project toEntity(ProjectDto projectDto) {
    mapper.typeMap(ProjectDto.class, Project.class)
        .addMappings(mapper -> mapper.skip(Project::setId));
    if (projectDto != null) {
      return mapper.map(projectDto, Project.class);
    } else return null;
  }

  public ProjectDto toUpdatedProjectDto(Map<String, Object> values, Project project) {
    if (values != null) {
      ProjectDto projectDto = toProjectDto(project);
      mapper.map(values, projectDto);
      projectDto.setUpdatedAt(new Date());
      projectDto.setId(project.getId());
      return projectDto;
    } return null;
  }

  public ProjectDto toProjectDto(Project project) {
    if(project != null) {
      return mapper.map(project, ProjectDto.class);
    } else {
      return null;
    }
  }
}
