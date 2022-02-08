package com.leverx.internship.project.security.service.impl;

import com.leverx.internship.project.security.jwt.JwtTokenProvider;
import com.leverx.internship.project.security.service.AuthService;
import com.leverx.internship.project.security.web.dto.request.AuthenticationRequestDto;
import com.leverx.internship.project.security.web.dto.response.AuthenticationResponseDto;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public AuthenticationResponseDto authentication(
      AuthenticationRequestDto authenticationRequestDto) {
    try {
      String email = authenticationRequestDto.getEmail();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, authenticationRequestDto.getPassword()));
      UserResponse user = userService.findByEmail(email);
      String token = jwtTokenProvider.createToken(email, user.getRole());

      return new AuthenticationResponseDto(email, token);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }
}
