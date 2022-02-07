package com.leverx.internship.project.project.web.dto;

import com.leverx.internship.project.user.web.dto.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectDto {
  private int id;
  private String name;
  private String description;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private List<UserDto> usersDto;
}
