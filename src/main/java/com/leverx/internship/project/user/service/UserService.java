package com.leverx.internship.project.user.service;

import com.leverx.internship.project.user.web.dto.UserDto;
import java.util.List;

public interface UserService {

  List<UserDto> findAll(int page, int size, String search);

  UserDto findById(int id);

  UserDto create(UserDto user);

  UserDto update(int id, UserDto userDto);

  void delete(int id);
}