package com.leverx.internship.project.department.web.dto.response;

import com.leverx.internship.project.user.web.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentUsersResponse {
  private int id;
  private String name;
  private List<UserResponse> employeesDto;

  public void addUser(UserResponse userResponse) {
    this.employeesDto.add(userResponse);
  }

  public void removeUser(UserResponse userResponse) {
    this.employeesDto.remove(userResponse);
  }
}
