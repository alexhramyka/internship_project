package com.leverx.internship.project.user.service.impl;

import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.service.filter.UserSpecificationsBuilder;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Transactional
  @Override
  public List<UserDto> findAll(int page, int size, String search) {
    UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
    Pageable paging = PageRequest.of(page, size);
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    Pattern emailPattern = Pattern.compile(
        "(\\w+?)(:|<|>)(([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}),");
    Matcher matcher = pattern.matcher(search + ",");
    Matcher emailMatcher = emailPattern.matcher(search + ",");
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }
    while (emailMatcher.find()) {
      builder.with(emailMatcher.group(1), emailMatcher.group(2), emailMatcher.group(3));
    }
    Specification<User> spec = builder.build();
    Page<User> userPage = userRepository.findAll(spec, paging);
    List<UserDto> usersDto = new ArrayList<>();
    userPage.forEach(user -> usersDto.add(userConverter.toUserDto(user)));
    return usersDto;
  }

  @Transactional
  @Override
  public UserDto findById(int id) {
    User user = getUser(id);
    return userConverter.toUserDto(user);
  }

  @Transactional
  @Override
  public String create(UserDto userDto) {
    User user = userConverter.toEntity(userDto);
    user.setCreatedAt(new Date());
    user.setUpdatedAt(new Date());
    return userRepository.save(user).toString();
  }

  @Transactional
  @Override
  public String update(int id, Map<String, Object> values) {
    User user = getUser(id);
    User newUser = userConverter.toEntity(userConverter.toUpdatedUserDto(values, user));
    newUser.setCreatedAt(user.getCreatedAt());
    newUser.setId(user.getId());
    newUser.setUpdatedAt(new Date());
    return userRepository.save(newUser).toString();
  }

  @Transactional
  @Override
  public String delete(int id) {
    getUser(id);
    userRepository.deleteById(id);
    return "User deleted successfully";
  }

  private User getUser(int id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User with id: " + id + " doesn't exist"));
  }
}
