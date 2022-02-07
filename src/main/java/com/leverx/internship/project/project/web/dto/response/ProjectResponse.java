package com.leverx.internship.project.project.web.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ProjectResponse {
  private int id;
  private String name;
  private String description;
  private LocalDate dateStart;
  private LocalDate dateEnd;
}
