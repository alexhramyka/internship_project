package com.leverx.internship.project.user.web.dto;

import com.leverx.internship.project.security.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
  private int id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;
  private boolean active;
  private Date createdAt;
  private Date updatedAt;
}