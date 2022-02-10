package com.leverx.internship.project.user.service.impl;

import com.leverx.internship.project.exception.JwtAuthenticationException;
import com.leverx.internship.project.security.model.Role;
import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.service.filter.UserSpecification;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.request.UserBodyRequest;
import com.leverx.internship.project.user.web.dto.request.UserParamRequest;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Transactional(readOnly = true)
  @Override
  public List<UserResponse> findAll(int page, int size, UserParamRequest params) {
    Specification<User> spec =
        Specification.where(
            UserSpecification.userParamHasEmail(params.getEmail())
                .and(UserSpecification.userParamHasFirstName(params.getFirstName()))
                .and(UserSpecification.userParamHasLastName(params.getLastName())));
    Pageable paging = PageRequest.of(page, size);
    Page<User> userPage = userRepository.findAll(spec, paging);
    return userConverter.userListToUserResponseList(userPage.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public UserResponse findByEmail(String email) {
    return userConverter.toUserResponse(
        userRepository
            .findUserByEmail(email)
            .orElseThrow(() -> new NotFoundException("Such user doesn't exist")));
  }

  @Transactional(readOnly = true)
  @Override
  public UserResponse findById(Integer id) {
    User user = getUser(id);
    return userConverter.toUserResponse(user);
  }

  @Transactional
  @Override
  public UserResponse create(UserBodyRequest userDto) {
    User user = userConverter.toEntity(userDto);
    user.setRole(Role.valueOf(userDto.getRole().toString()));
    user.setCreatedAt(LocalDate.now());
    user.setUpdatedAt(LocalDate.now());
    return userConverter.toUserResponse(userRepository.save(user));
  }

  @Transactional
  @Override
  public UserResponse update(Integer id, UserBodyRequest userBodyRequest) {
    User user = getUser(id);
    User newUser =
        userConverter.toEntity(userConverter.toUpdatedUserResponse(userBodyRequest, user));
    newUser.setCreatedAt(user.getCreatedAt());
    newUser.setId(user.getId());
    newUser.setUpdatedAt(LocalDate.now());
    return userConverter.toUserResponse(userRepository.save(newUser));
  }

  @Transactional
  @Override
  public void delete(Integer id) {
    getUser(id);
    userRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public UserDetails getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return Optional.of(((UserDetails)principal)).orElseThrow(() -> new JwtAuthenticationException("Authentication exception"));
  }

  @Transactional(readOnly = true)
  @Override
  public UserResponse findUserByEmail(String email) {
    return userConverter.toUserResponse(
        userRepository
        .findUserByEmail(email)
        .orElseThrow(() -> new NotFoundException("User with email: " + email + " doesn't exist")));
  }

  @Transactional(readOnly = true)
  @Override
  public User getUser(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("User with id: " + id + " doesn't exist"));
  }
}