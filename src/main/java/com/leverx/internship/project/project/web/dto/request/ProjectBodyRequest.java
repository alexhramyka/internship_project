package com.leverx.internship.project.project.web.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ProjectBodyRequest {
  private String name;
  private String description;
  private LocalDate dateStart;
  private LocalDate dateEnd;
}
