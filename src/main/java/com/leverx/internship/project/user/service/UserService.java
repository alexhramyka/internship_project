package com.leverx.internship.project.user.service;

import com.leverx.internship.project.user.web.dto.UserDto;
import java.util.List;
import java.util.Map;

public interface UserService {

  List<UserDto> findAll(int page, int size, String search);

  UserDto findById(int id);

  String create(UserDto user);

  String update(int id, Map<String, Object> values);

  String delete(int id);
}