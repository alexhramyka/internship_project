package com.leverx.internship.project.user.web.converter;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.web.dto.request.UserBodyRequest;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
  private final ModelMapper mapper;

  public User toEntity(@NonNull UserBodyRequest userBodyRequest) {
    return mapper.map(userBodyRequest, User.class);
  }

  public User toEntity(@NonNull UserResponse userResponse) {
    return mapper.map(userResponse, User.class);
  }

  public UserResponse toUpdatedUserResponse(@NonNull UserBodyRequest userReqToUpdate, User user) {
    UserResponse userResponse = toUserResponse(user);
    mapper.map(userReqToUpdate, userResponse);
    return userResponse;
  }

  public UserResponse toUserResponse(@NonNull User user) {
    return mapper.map(user, UserResponse.class);
  }

  public List<UserResponse> userListToUserResponseList(List<User> users) {
    if (users != null) {
      List<UserResponse> userResponseList = new ArrayList<>();
      users.forEach(user -> userResponseList.add(toUserResponse(user)));
      return userResponseList;
    } else {
      return new ArrayList<>();
    }
  }

  public List<User> usersRespListToUserList(List<UserResponse> usersResponse) {
    if (usersResponse != null) {
      List<User> users = new ArrayList<>();
      usersResponse.forEach(userResponse -> users.add(toEntity(userResponse)));
      return users;
    } else {
      return new ArrayList<>();
    }
  }

  public Set<UserResponse> usersSetToUsersResponseSet(Set<User> users) {
    if (users != null) {
      Set<UserResponse> usersResponse = new HashSet<>();
      users.forEach(user -> usersResponse.add(toUserResponse(user)));
      return usersResponse;
    } else {
      return new HashSet<>();
    }
  }

  public Set<User> usersResponseSetToUserSet(Set<UserResponse> userResponseSet) {
    if (userResponseSet != null) {
      Set<User> users = new HashSet<>();
      userResponseSet.forEach(userResponse -> users.add(toEntity(userResponse)));
      return users;
    } else {
      return new HashSet<>();
    }
  }
}