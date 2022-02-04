package com.leverx.internship.project.project.web.converter;

import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.web.dto.ProjectDto;
import com.leverx.internship.project.user.web.converter.UserConverter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectConverter {
  private final ModelMapper mapper;
  private final UserConverter userConverter;

  public Project toEntity(@NonNull ProjectDto projectDto) {
    mapper.typeMap(ProjectDto.class, Project.class)
        .addMappings(mapper -> mapper.skip(Project::setId))
        .addMappings(mapper -> mapper.skip(Project::setEmployees))
        .setPostConverter(usersDtoListToUsersListConverter());
      return mapper.map(projectDto, Project.class);
  }

  public ProjectDto toUpdatedProjectDto(@NonNull ProjectDto projectDtoToUpdate, Project project) {
      ProjectDto projectDto = toProjectDto(project);
      mapper.map(projectDtoToUpdate, projectDto);
      return projectDto;
  }

  public ProjectDto toProjectDto(@NonNull Project project) {
    mapper.typeMap(Project.class, ProjectDto.class)
        .setPostConverter(usersListToUsersDtoListConverter());
      return mapper.map(project, ProjectDto.class);
    }

  public Converter<ProjectDto, Project> usersDtoListToUsersListConverter() {
    return mappingContext -> {
      ProjectDto source = mappingContext.getSource();
      Project destination = mappingContext.getDestination();
      destination.setEmployees(userConverter.usersDtoListToUserList(source.getUsersDto()));
      return mappingContext.getDestination();
    };
  }

  public Converter<Project, ProjectDto> usersListToUsersDtoListConverter() {
    return mappingContext -> {
      Project source = mappingContext.getSource();
      ProjectDto destination = mappingContext.getDestination();
      destination.setUsersDto(userConverter.userListToUserDtoList(source.getEmployees()));
      return mappingContext.getDestination();
    };
  }

}
