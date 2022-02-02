package com.leverx.internship.project.user.web.dto;

import com.leverx.internship.project.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private int id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;
  private boolean active;
}