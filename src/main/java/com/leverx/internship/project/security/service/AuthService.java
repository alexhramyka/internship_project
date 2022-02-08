package com.leverx.internship.project.security.service;

import com.leverx.internship.project.security.web.dto.request.AuthenticationRequestDto;
import com.leverx.internship.project.security.web.dto.response.AuthenticationResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
  AuthenticationResponseDto authentication(AuthenticationRequestDto authenticationRequestDto);

  void logout(HttpServletRequest request, HttpServletResponse response);
}
