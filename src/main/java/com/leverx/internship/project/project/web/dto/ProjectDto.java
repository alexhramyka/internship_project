package com.leverx.internship.project.project.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
  private int id;
  private String name;
  private String description;
  private String dateStart;
  private Date dateEnd;
  private Date createdAt;
  private Date updatedAt;
}
