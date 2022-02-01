package com.leverx.internship.project.user.web.converter;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.web.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class UserConverter {
  private final ModelMapper mapper = new ModelMapper();

  public User toEntity(UserDto userDto) {
    mapper.typeMap(UserDto.class, User.class)
        .addMappings(mapper -> mapper.skip(User::setId));
    if (userDto != null) {
      return mapper.map(userDto, User.class);
    } else return null;
  }

  public UserDto toUpdatedUserDto(Map<String, Object> values, User user) {
    if (values != null) {
      UserDto userDto = toUserDto(user);
      mapper.map(values, userDto);
      userDto.setUpdatedAt(new Date());
      return userDto;
    } return null;
  }

  public UserDto toUserDto(User user) {
    if(user != null) {
      return mapper.map(user, UserDto.class);
    } else {
      return null;
    }
  }
}