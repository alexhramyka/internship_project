package com.leverx.internship.project.csv.model;

import com.leverx.internship.project.security.model.Role;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CsvUser {
  @CsvBindByName(column = "EMAIL", required = true)
  private String email;

  @CsvBindByName(column = "PASSWORD", required = true)
  private String password;

  @CsvBindByName(column = "FIRSTNAME", required = true)
  private String firstName;

  @CsvBindByName(column = "LASTNAME", required = true)
  private String lastName;

  @CsvBindByName(column = "ROLE", required = true)
  private Role role;

  @CsvBindByName(column = "ACTIVE", required = true)
  private boolean active;

  private LocalDate createdAt = LocalDate.now();
  private LocalDate updatedAt = LocalDate.now();
}
