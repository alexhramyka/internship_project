package com.leverx.internship.project.project.service.impl;

import com.leverx.internship.project.project.repository.ProjectRepository;
import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.service.ProjectService;
import com.leverx.internship.project.project.web.converter.ProjectConverter;
import com.leverx.internship.project.project.web.dto.request.ProjectBodyRequest;
import com.leverx.internship.project.project.web.dto.request.ProjectParamRequest;
import com.leverx.internship.project.project.web.dto.response.ProjectResponse;
import com.leverx.internship.project.project.web.dto.response.ProjectUsersResponse;
import com.leverx.internship.project.security.Role;
import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.converter.UserConverter;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

  @Mock private ProjectRepository projectRepository;
  @Mock private UserService userService;
  private ProjectService projectServiceUnderTest;
  @Mock private ProjectConverter projectConverter;
  @Mock private UserConverter userConverter;

  @BeforeEach
  public void init() {
    projectServiceUnderTest =
        new ProjectServiceImpl(projectRepository, projectConverter, userService, userConverter) {};
  }

  @Test
  void findAllPositiveTest() {
    ProjectResponse projectDto = new ProjectResponse();
    projectDto.setName("name");
    projectDto.setDescription("description");
    projectDto.setDateStart(LocalDate.now());
    projectDto.setDateEnd(LocalDate.now());
    List<ProjectResponse> expected = new ArrayList<>();
    expected.add(projectDto);
    Project project = new Project();
    project.setName("name");
    project.setDescription("description");
    project.setDateStart(LocalDate.now());
    project.setDateEnd(LocalDate.now());
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());
    Page<Project> projects = new PageImpl<>(List.of(project));
    when(projectRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(projects);

    ProjectResponse projectDto1 = new ProjectResponse();
    projectDto1.setName("name");
    projectDto1.setDescription("description");
    projectDto1.setDateStart(LocalDate.now());
    projectDto1.setDateEnd(LocalDate.now());
    when(projectConverter.toProjectResponse(any(Project.class))).thenReturn(projectDto1);

    List<ProjectResponse> actual = projectServiceUnderTest.findAll(0, 3, new ProjectParamRequest("name", null));

    assertEquals(expected.size(), actual.size());
  }

  @Test
  void saveUserPositiveTest() {
    ProjectResponse expected = new ProjectResponse();
    expected.setName("name");
    expected.setDescription("description");
    expected.setDateStart(LocalDate.now());
    expected.setDateEnd(LocalDate.now());

    ProjectBodyRequest projectDto = new ProjectBodyRequest();
    projectDto.setName("name");
    projectDto.setDescription("description");
    projectDto.setDateStart(LocalDate.now());
    projectDto.setDateEnd(LocalDate.now());

    Project project = new Project();
    project.setName("name");
    project.setDescription("description");
    project.setDateStart(LocalDate.now());
    project.setDateEnd(LocalDate.now());
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());
    when(projectConverter.toEntity(projectDto)).thenReturn(project);

    ProjectResponse projectDto1 = new ProjectResponse();
    projectDto1.setName("name");
    projectDto1.setDescription("description");
    projectDto1.setDateStart(LocalDate.now());
    projectDto1.setDateEnd(LocalDate.now());
    when(projectConverter.toProjectResponse(any(Project.class))).thenReturn(projectDto1);

    Project project1 = new Project();
    project1.setName("name");
    project1.setDescription("description");
    project1.setDateStart(LocalDate.now());
    project1.setDateEnd(LocalDate.now());
    project1.setCreatedAt(LocalDate.now());
    project1.setUpdatedAt(LocalDate.now());
    when(projectRepository.save(any(Project.class))).thenReturn(project1);

    final ProjectResponse result = projectServiceUnderTest.create(projectDto);

    assertEquals(expected, result);
  }

  @Test
  void FindByIdPositiveTest() {
    ProjectResponse expected = new ProjectResponse();
    expected.setId(1);
    expected.setName("name");
    expected.setDescription("description");
    expected.setDateStart(LocalDate.now());
    expected.setDateEnd(LocalDate.now());

    Project project = new Project();
    project.setId(1);
    project.setName("name");
    project.setDescription("description");
    project.setDateStart(LocalDate.now());
    project.setDateEnd(LocalDate.now());
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());
    Optional<Project> projectOptional = Optional.of(project);
    when(projectRepository.findById(1)).thenReturn(projectOptional);

    ProjectResponse projectDto = new ProjectResponse();
    projectDto.setId(1);
    projectDto.setName("name");
    projectDto.setDescription("description");
    projectDto.setDateStart(LocalDate.now());
    projectDto.setDateEnd(LocalDate.now());
    when(projectConverter.toProjectResponse(any(Project.class))).thenReturn(projectDto);

    final ProjectResponse result = projectServiceUnderTest.findById(1);

    assertEquals(expected, result);
  }

  @Test
  void updateUserPositiveTest() {
    ProjectResponse expected = new ProjectResponse();
    expected.setId(1);
    expected.setName("name");
    expected.setDescription("new description");
    expected.setDateStart(LocalDate.now());
    expected.setDateEnd(LocalDate.now());

    ProjectBodyRequest projectDtoToUpdate = new ProjectBodyRequest();
    projectDtoToUpdate.setDescription("new description");

    Project project = new Project();
    project.setId(1);
    project.setName("name");
    project.setDescription("description");
    project.setDateStart(LocalDate.now());
    project.setDateEnd(LocalDate.now());
    project.setCreatedAt(LocalDate.now());
    project.setUpdatedAt(LocalDate.now());

    Optional<Project> projectOptional = Optional.of(project);
    when(projectRepository.findById(1)).thenReturn(projectOptional);

    Project project1 = new Project();
    project1.setName("name");
    project1.setDescription("description");
    project1.setDateStart(LocalDate.now());
    project1.setDateEnd(LocalDate.now());
    project1.setCreatedAt(LocalDate.now());
    project1.setUpdatedAt(LocalDate.now());
    when(projectConverter.toEntity(any(ProjectResponse.class))).thenReturn(project1);

    ProjectResponse projectDto = new ProjectResponse();
    projectDto.setId(1);
    projectDto.setName("name");
    projectDto.setDescription("new description");
    projectDto.setDateStart(LocalDate.now());
    projectDto.setDateEnd(LocalDate.now());
    when(projectConverter.toUpdatedProjectResponse(eq(projectDtoToUpdate), any(Project.class)))
        .thenReturn(projectDto);
    when(projectConverter.toProjectResponse(any(Project.class))).thenReturn(projectDto);

    Project project2 = new Project();
    project2.setName("name");
    project2.setDescription("new description");
    project2.setDateStart(LocalDate.now());
    project2.setDateEnd(LocalDate.now());
    project2.setCreatedAt(LocalDate.now());
    project2.setUpdatedAt(LocalDate.now());
    when(projectRepository.save(any(Project.class))).thenReturn(project2);

    final ProjectResponse result = projectServiceUnderTest.update(1, projectDtoToUpdate);
    result.setId(1);
    assertEquals(expected, result);
  }

  @Test
  void deletePositiveTest() {
    projectServiceUnderTest.delete(1);

    verify(projectRepository).deleteById(1);
  }

  @Test
  void testAddUserToProject() {
    ProjectUsersResponse expected = new ProjectUsersResponse();
    expected.setId(1);
    expected.setName("name");
    expected.setUsers(
        Set.of(new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true)));

    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setEmail("email@mail.ru");
    user.setPassword("password");
    user.setActive(true);
    user.setRole(Role.ADMIN);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    user.setProjects(Set.of(new Project(1, "name", "description", LocalDate.of(2022, 2 ,1), LocalDate.of(2022, 2, 1) , LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), 0, 0, Set.of(user))));

    Optional<Project> projectOptional =
        Optional.of(
            new Project(
                1,
                "name",
                "description",
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                0,
                0,
                new HashSet<>()));
    projectOptional.get().getEmployees().add(user);
    when(projectRepository.findById(1)).thenReturn(projectOptional);

    ProjectUsersResponse projectUsersResponse = new ProjectUsersResponse();
    projectUsersResponse.setId(1);
    projectUsersResponse.setName("name");
    projectUsersResponse.setUsers(new HashSet<>());
    when(projectConverter.toProjectUsersResponse(any(Project.class))).thenReturn(projectUsersResponse);

    UserResponse userResponse = new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);
    when(userService.findById(1)).thenReturn(userResponse);

    User user2 = new User();
    user2.setId(1);
    user2.setFirstName("Ivan");
    user2.setLastName("Ivanov");
    user2.setEmail("email@mail.ru");
    user2.setPassword("password");
    user2.setActive(true);
    user2.setRole(Role.ADMIN);
    user2.setCreatedAt(LocalDate.of(2022, 2, 1));
    user2.setUpdatedAt(LocalDate.of(2022, 2, 1));

    final Set<User> users = Set.of(user2);
    when(userConverter.usersResponseSetToUserSet(Set.of(new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true)))).thenReturn(users);

    final User user3 = new User();
    user3.setId(1);
    user3.setFirstName("firstName");
    user3.setLastName("lastName");
    user3.setEmail("email");
    user3.setPassword("password");
    user3.setActive(false);
    user3.setRole(Role.ADMIN);
    user3.setCreatedAt(LocalDate.of(2020, 1, 1));
    user3.setUpdatedAt(LocalDate.of(2020, 1, 1));

    Project project =
        new Project(
            1,
            "name",
            "description",
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            0,
            0,
            new HashSet<>());
    when(projectRepository.save(any(Project.class))).thenReturn(project);

    ProjectUsersResponse result = projectServiceUnderTest.addUserToProject(1, 1);

    assertEquals(expected, result);
    verify(projectRepository).save(any(Project.class));
  }

  @Test
  void testDeleteUserFromProject() {
    User user = new User();
    user.setId(1);
    user.setFirstName("Ivan");
    user.setLastName("Ivanov");
    user.setEmail("email@mail.ru");
    user.setPassword("password");
    user.setActive(true);
    user.setRole(Role.ADMIN);
    user.setCreatedAt(LocalDate.of(2022, 2, 1));
    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
    UserResponse userDto =
        new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);

    when(userService.findById(1)).thenReturn(userDto);

    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Ivan");
    user1.setLastName("Ivanov");
    user1.setEmail("email@mail.ru");
    user1.setPassword("password");
    user1.setActive(true);
    user1.setRole(Role.ADMIN);
    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
    Optional<Project> projectOptional =
        Optional.of(
            new Project(
                1,
                "name",
                "description",
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 2, 1),
                0,
                0,
                Set.of(user)));
    when(projectRepository.findById(1)).thenReturn(projectOptional);

    ProjectUsersResponse projectUsersResponse = new ProjectUsersResponse();
    projectUsersResponse.setId(1);
    projectUsersResponse.setName("name");
    projectUsersResponse.setUsers(new HashSet<>());
    projectUsersResponse.addUser(userDto);
    when(projectConverter.toProjectUsersResponse(any(Project.class))).thenReturn(projectUsersResponse);

    User user2 = new User();
    user2.setId(1);
    user2.setFirstName("Ivan");
    user2.setLastName("Ivanov");
    user2.setEmail("email@mail.ru");
    user2.setPassword("password");
    user2.setActive(true);
    user2.setRole(Role.ADMIN);
    user2.setCreatedAt(LocalDate.of(2022, 2, 1));
    user2.setUpdatedAt(LocalDate.of(2022, 2, 1));

    Project project =
        new Project(
            1,
            "name",
            "description",
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            LocalDate.of(2022, 2, 1),
            0,
            0,
            new HashSet<>());
    project.getEmployees().add(user2);
    when(projectRepository.save(any(Project.class))).thenReturn(project);

    projectServiceUnderTest.deleteUserFromProject(1,1);

    verify(projectRepository).save(any(Project.class));
  }

}