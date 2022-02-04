package com.leverx.internship.project.user.service.impl;

import com.leverx.internship.project.security.Role;
import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock private UserRepository userRepository;
  private UserService userService;
  @Mock private UserConverter userConverter;

  UserServiceTest() {}

  @BeforeEach
  public void initUseCase() {
    userService = new UserServiceImpl(userRepository, userConverter) {};
  }

  @Test
  void findAllPositiveTest() {
    UserDto userDto =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    List<UserDto> expected = new ArrayList<>();
    expected.add(userDto);
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    final Page<User> users =
        new PageImpl<>(
            List.of(user));
    when(userRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(users);

    UserDto userDto1 =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    List<UserDto> usersDto = List.of(userDto1);
    when(userConverter.userListToUserDtoList(users.toList())).thenReturn(usersDto);

    List<UserDto> actual = userService.findAll(0, 3, "firstName:Ivan");

    assertEquals(expected.size(), actual.size());
  }

  @Test
  void saveUserPositiveTest() {
    UserDto expected = new UserDto(
        1,
        "mail1@mail.ru",
        "password",
        "Ivan",
        "Ivanov",
        Role.ADMIN,
        true);

    UserDto userDto =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);

    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    when(userConverter.toEntity(userDto)).thenReturn(user);

    final UserDto userDto1 = new UserDto(1, "mail1@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);
    when( userConverter .toUserDto(any(User.class))).thenReturn(userDto1);

    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Ivan");
    user1.setLastName("Ivanov");
    user1.setRole(Role.ADMIN);
    user1.setActive(true);
    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
    when(userRepository.save(any(User.class))).thenReturn(user1);

    final UserDto result = userService.create(userDto);

    assertEquals(expected, result);
  }

  @Test
  void FindByIdPositiveTest() {
    UserDto expected =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    final Optional<User> userOptional =
        Optional.of(user);
    when(userRepository.findById(1)).thenReturn(userOptional);
    UserDto userDto =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    when(userConverter.toUserDto(any(User.class))).thenReturn(userDto);

    // Run the test
    final UserDto result = userService.findById(1);

    assertEquals(expected, result);
  }

  @Test
  void updateUserPositiveTest() {
    final UserDto expected = new UserDto(1, "mail1@mail.ru", "password", "Alex", "Ivanov", Role.ADMIN, true);
    final UserDto userDtoUpdate = new UserDto();
    userDtoUpdate.setFirstName("Alex");
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setEmail("email");
    user.setPassword("password");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    final Optional<User> userOptional =
        Optional.of(user);
    when(userRepository.findById(1)).thenReturn(userOptional);

    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Ivan");
    user1.setLastName("Ivanov");
    user1.setEmail("email");
    user1.setPassword("password");
    user1.setRole(Role.ADMIN);
    user1.setActive(true);
    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
    when(userConverter.toEntity(any(UserDto.class))).thenReturn(user1);

    final UserDto userDto =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Alex",
            "Ivanov",
            Role.ADMIN,
            true);
    when(userConverter.toUpdatedUserDto(
        eq(userDtoUpdate), any(User.class)))
        .thenReturn(userDto);
    when( userConverter.toUserDto(any(User.class))).thenReturn(userDto);
    User user2 = new User();
    user2.setId(1);
    user2.setPassword("password");
    user2.setEmail("email");
    user2.setFirstName("Alex");
    user2.setLastName("Ivanov");
    user2.setRole(Role.ADMIN);
    user2.setActive(true);
    user2.setCreatedAt(LocalDate.of(2022, 2, 1));
    user2.setUpdatedAt(LocalDate.of(2022, 2, 1));
    when(userRepository.save(any(User.class))).thenReturn(user2);

    final UserDto result = userService.update(1, userDtoUpdate);

    assertEquals(expected, result);
  }

  @Test
  void deletePositiveTest() {
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    final Optional<User> userOptional =
        Optional.of(user);
    when(userRepository.findById(1)).thenReturn(userOptional);

    userService.delete(1);
  }
}