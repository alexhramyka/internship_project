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
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
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
    when(userConverter.toUserDto(any(User.class))).thenReturn(userDto1);

    List<UserDto> actual = userService.findAll(0, 3, "firstName:Ivan");

    assertEquals(actual.size(), expected.size());
  }

  @Test
  void saveUserPositiveTest() {

    UserDto user =
        new UserDto(
            1,
            "mail1@mail.ru",
            "password",
            "Ivan",
            "Ivanov",
            Role.ADMIN,
            true);
    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Ivan");
    user1.setLastName("Ivanov");
    user1.setRole(Role.ADMIN);
    user1.setActive(true);
    user1.setCreatedAt(new Date());
    user1.setUpdatedAt(new Date());
    when(userRepository.save(any(User.class))).thenReturn(user1);

    User user2 = new User();
    user2.setId(1);
    user2.setFirstName("Ivan");
    user2.setLastName("Ivanov");
    user2.setRole(Role.ADMIN);
    user2.setActive(true);
    user2.setCreatedAt(new Date());
    user2.setUpdatedAt(new Date());
    when(userConverter.toEntity(user)).thenReturn(user2);

    final String result = userService.create(user);

    assertEquals(user2.toString(), result);
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
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
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
    final Map<String, Object> values = Map.ofEntries(Map.entry("firstName", "Alex"));
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    final Optional<User> userOptional =
        Optional.of(user);
    when(userRepository.findById(1)).thenReturn(userOptional);

    User user1 = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
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
            eq(Map.ofEntries(Map.entry("firstName", "Alex"))), any(User.class)))
        .thenReturn(userDto);

    User user2 = new User();
    user.setId(1);
    user.setFirstName("Alex");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    when(userRepository.save(any(User.class))).thenReturn(user2);

    final String result = userService.update(1, values);

    assertEquals(user2.toString(), result);
  }

  @Test
  void deletePositiveTest() {
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setRole(Role.ADMIN);
    user.setActive(true);
    user.setCreatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    user.setUpdatedAt(new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
    final Optional<User> userOptional =
        Optional.of(user);
    when(userRepository.findById(1)).thenReturn(userOptional);

    final String result = userService.delete(1);

    assertEquals("User deleted successfully", result);
  }
}