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

  public UserResponse toUpdatedUserDto(@NonNull UserBodyRequest userDtoUpdate, User user) {
    UserResponse userDto = toUserResponse(user);
    mapper.map(userDtoUpdate, userDto);
    return userDto;
  }

  public UserResponse toUserResponse(@NonNull User user) {
    return mapper.map(user, UserResponse.class);
  }

  public List<UserResponse> userListToUserDtoList(List<User> users) {
    if (users != null) {
      List<UserResponse> usersDto = new ArrayList<>();
      users.forEach(user -> usersDto.add(toUserResponse(user)));
      return usersDto;
    } else {
      return new ArrayList<>();
    }
  }

  public List<User> usersDtoListToUserList(List<UserResponse> usersDto) {
    if (usersDto != null) {
      List<User> users = new ArrayList<>();
      usersDto.forEach(userDto -> users.add(toEntity(userDto)));
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

  public Set<User> usersDtoSetToUserSet(Set<UserResponse> usersDto) {
    if (usersDto != null) {
      Set<User> users = new HashSet<>();
      usersDto.forEach(userDto -> users.add(toEntity(userDto)));
      return users;
    } else {
      return new HashSet<>();
    }
  }
}