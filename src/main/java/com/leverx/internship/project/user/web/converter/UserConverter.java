package com.leverx.internship.project.user.web.converter;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.web.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
  private final ModelMapper mapper;

  public User toEntity(@NonNull UserDto userDto) {
    mapper.typeMap(UserDto.class, User.class).addMappings(mapper -> mapper.skip(User::setId));
    return mapper.map(userDto, User.class);
  }

  public UserDto toUpdatedUserDto(@NonNull UserDto userDtoUpdate, User user) {
    UserDto userDto = toUserDto(user);
    mapper.map(userDtoUpdate, userDto);
    return userDto;
  }

  public UserDto toUserDto(@NonNull User user) {
    return mapper.map(user, UserDto.class);
  }
}