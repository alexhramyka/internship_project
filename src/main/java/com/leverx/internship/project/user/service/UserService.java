package com.leverx.internship.project.user.service;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.web.dto.request.UserBodyRequest;
import com.leverx.internship.project.user.web.dto.request.UserParamRequest;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import java.util.List;

public interface UserService {

  List<UserResponse> findAll(int page, int size, UserParamRequest params);

  UserResponse findByEmail(String email);

  UserResponse findById(Integer id);

  UserResponse create(UserBodyRequest user);

  UserResponse update(Integer id, UserBodyRequest userDto);

  void delete(Integer id);

  User getUser(Integer id);
}