package com.leverx.internship.project.user.service.impl;

import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.service.filter.UserSpecificationsBuilder;
import com.leverx.internship.project.user.service.util.EmailMatcher;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.UserDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String PARAMS_PATTERN = "(\\w+?)(:|<|>)(\\w+?),";
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> findAll(int page, int size, String search) {
    UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
    Pageable paging = PageRequest.of(page, size);
    Pattern pattern = Pattern.compile(PARAMS_PATTERN);
    Matcher matcher = pattern.matcher(search + ",");
    EmailMatcher.buildSpec(builder, search);
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }
    Specification<User> spec = builder.build();
    Page<User> userPage = userRepository.findAll(spec, paging);
    return userConverter.userListToUserDtoList(userPage.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public UserDto findById(int id) {
    User user = getUser(id);
    return userConverter.toUserDto(user);
  }

  @Transactional
  @Override
  public UserDto create(UserDto userDto) {
    User user = userConverter.toEntity(userDto);
    user.setCreatedAt(LocalDate.now());
    user.setUpdatedAt(LocalDate.now());
    return userConverter.toUserDto(userRepository.save(user));
  }

  @Transactional
  @Override
  public UserDto update(int id, UserDto userDtoUpdate) {
    User user = getUser(id);
    User newUser = userConverter.toEntity(userConverter.toUpdatedUserDto(userDtoUpdate, user));
    newUser.setCreatedAt(user.getCreatedAt());
    newUser.setId(user.getId());
    newUser.setUpdatedAt(LocalDate.now());
    return userConverter.toUserDto(userRepository.save(newUser));
  }

  @Transactional
  @Override
  public void delete(int id) {
    getUser(id);
    userRepository.deleteById(id);
  }

  private User getUser(int id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User with id: " + id + " doesn't exist"));
  }
}
