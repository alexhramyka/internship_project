package com.leverx.internship.project.user.service.impl;

import com.leverx.internship.project.security.Role;
import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.request.UserBodyRequest;
import com.leverx.internship.project.user.web.dto.request.UserParamRequest;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
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
  private UserService userServiceUnderTest;
  @Mock private UserConverter userConverter;

  @BeforeEach
  public void init() {
    userServiceUnderTest = new UserServiceImpl(userRepository, userConverter) {};
  }

  @Test
  void findAllPositiveTest() {
    UserResponse userDto =
        new UserResponse(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    List<UserResponse> expected = new ArrayList<>();
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

    UserResponse userDto1 =
        new UserResponse(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    List<UserResponse> usersDto = List.of(userDto1);
    when(userConverter.userListToUserResponseList(users.toList())).thenReturn(usersDto);

    List<UserResponse> actual = userServiceUnderTest.findAll(0, 3, new UserParamRequest("Alex", null, null));

    assertEquals(expected.size(), actual.size());
  }

  @Test
  void saveUserPositiveTest() {
    UserResponse expected = new UserResponse(
        1,
        "mail1@mail.ru",
        "password",
        "Ivan",
        "Ivanov",
        Role.ADMIN,
        true);

    UserBodyRequest userDto =
        new UserBodyRequest(
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

    final UserResponse userDto1 = new UserResponse(1, "mail1@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);
    when( userConverter .toUserResponse(any(User.class))).thenReturn(userDto1);

    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Ivan");
    user1.setLastName("Ivanov");
    user1.setRole(Role.ADMIN);
    user1.setActive(true);
    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
    when(userRepository.save(any(User.class))).thenReturn(user1);

    final UserResponse result = userServiceUnderTest.create(userDto);

    assertEquals(expected, result);
  }

  @Test
  void FindByIdPositiveTest() {
    UserResponse expected =
        new UserResponse(
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
    UserResponse userDto =
        new UserResponse(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    when(userConverter.toUserResponse(any(User.class))).thenReturn(userDto);

    // Run the test
    final UserResponse result = userServiceUnderTest.findById(1);

    assertEquals(expected, result);
  }

  @Test
  void updateUserPositiveTest() {
    final UserResponse expected = new UserResponse(1, "mail1@mail.ru", "password", "Alex", "Ivanov", Role.ADMIN, true);
    final UserBodyRequest userDtoUpdate = new UserBodyRequest();
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
    when(userConverter.toEntity(any(UserResponse.class))).thenReturn(user1);

    final UserResponse userDto =
        new UserResponse(
            1,
            "mail1@mail.ru",
            "password",
            "Alex",
            "Ivanov",
            Role.ADMIN,
            true);
    when(userConverter.toUpdatedUserResponse(
        eq(userDtoUpdate), any(User.class)))
        .thenReturn(userDto);
    when( userConverter.toUserResponse(any(User.class))).thenReturn(userDto);
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

    final UserResponse result = userServiceUnderTest.update(1, userDtoUpdate);

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

    userServiceUnderTest.delete(1);
  }
}