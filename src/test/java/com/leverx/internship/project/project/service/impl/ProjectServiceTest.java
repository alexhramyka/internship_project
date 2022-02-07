//package com.leverx.internship.project.project.service.impl;
//
//import com.leverx.internship.project.project.repository.ProjectRepository;
//import com.leverx.internship.project.project.repository.entity.Project;
//import com.leverx.internship.project.project.service.ProjectService;
//import com.leverx.internship.project.project.web.converter.ProjectConverter;
//import com.leverx.internship.project.project.web.dto.response.ProjectDto;
//import com.leverx.internship.project.security.Role;
//import com.leverx.internship.project.user.repository.entity.User;
//import com.leverx.internship.project.user.service.UserService;
//import com.leverx.internship.project.user.web.converter.UserConverter;
//import com.leverx.internship.project.user.web.dto.response.UserResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ProjectServiceTest {
//
//  @Mock private ProjectRepository projectRepository;
//  @Mock private UserService userService;
//  private ProjectService projectService;
//  @Mock private ProjectConverter projectConverter;
//  @Mock private UserConverter userConverter;
//
//  ProjectServiceTest() {}
//
//  @BeforeEach
//  public void initUseCase() {
//    projectService =
//        new ProjectServiceImpl(projectRepository, projectConverter, userService, userConverter) {};
//  }
//
//  @Test
//  void findAllPositiveTest() {
//    ProjectDto projectDto = new ProjectDto();
//    projectDto.setName("name");
//    projectDto.setDescription("description");
//    projectDto.setDateStart(LocalDate.now());
//    projectDto.setDateEnd(LocalDate.now());
//    List<ProjectDto> expected = new ArrayList<>();
//    expected.add(projectDto);
//    Project project = new Project();
//    project.setName("name");
//    project.setDescription("description");
//    project.setDateStart(LocalDate.now());
//    project.setDateEnd(LocalDate.now());
//    project.setCreatedAt(LocalDate.now());
//    project.setUpdatedAt(LocalDate.now());
//    Page<Project> projects = new PageImpl<>(List.of(project));
//    when(projectRepository.findAll(any(Pageable.class))).thenReturn(projects);
//
//    ProjectDto projectDto1 = new ProjectDto();
//    projectDto1.setName("name");
//    projectDto1.setDescription("description");
//    projectDto1.setDateStart(LocalDate.now());
//    projectDto1.setDateEnd(LocalDate.now());
//    when(projectConverter.toProjectDto(any(Project.class))).thenReturn(projectDto);
//
//    List<ProjectDto> actual = projectService.findAll(0, 3);
//
//    assertEquals(expected.size(), actual.size());
//  }
//
//  @Test
//  void saveUserPositiveTest() {
//    ProjectDto expected = new ProjectDto();
//    expected.setName("name");
//    expected.setDescription("description");
//    expected.setDateStart(LocalDate.now());
//    expected.setDateEnd(LocalDate.now());
//
//    ProjectDto projectDto = new ProjectDto();
//    projectDto.setName("name");
//    projectDto.setDescription("description");
//    projectDto.setDateStart(LocalDate.now());
//    projectDto.setDateEnd(LocalDate.now());
//
//    Project project = new Project();
//    project.setName("name");
//    project.setDescription("description");
//    project.setDateStart(LocalDate.now());
//    project.setDateEnd(LocalDate.now());
//    project.setCreatedAt(LocalDate.now());
//    project.setUpdatedAt(LocalDate.now());
//    when(projectConverter.toEntity(projectDto)).thenReturn(project);
//
//    ProjectDto projectDto1 = new ProjectDto();
//    projectDto1.setName("name");
//    projectDto1.setDescription("description");
//    projectDto1.setDateStart(LocalDate.now());
//    projectDto1.setDateEnd(LocalDate.now());
//    when(projectConverter.toProjectDto(any(Project.class))).thenReturn(projectDto1);
//
//    Project project1 = new Project();
//    project1.setName("name");
//    project1.setDescription("description");
//    project1.setDateStart(LocalDate.now());
//    project1.setDateEnd(LocalDate.now());
//    project1.setCreatedAt(LocalDate.now());
//    project1.setUpdatedAt(LocalDate.now());
//    when(projectRepository.save(any(Project.class))).thenReturn(project1);
//
//    final ProjectDto result = projectService.create(projectDto);
//
//    assertEquals(expected, result);
//  }
//
//  @Test
//  void FindByIdPositiveTest() {
//    ProjectDto expected = new ProjectDto();
//    expected.setId(1);
//    expected.setName("name");
//    expected.setDescription("description");
//    expected.setDateStart(LocalDate.now());
//    expected.setDateEnd(LocalDate.now());
//
//    Project project = new Project();
//    project.setId(1);
//    project.setName("name");
//    project.setDescription("description");
//    project.setDateStart(LocalDate.now());
//    project.setDateEnd(LocalDate.now());
//    project.setCreatedAt(LocalDate.now());
//    project.setUpdatedAt(LocalDate.now());
//    Optional<Project> projectOptional = Optional.of(project);
//    when(projectRepository.findById(1)).thenReturn(projectOptional);
//
//    ProjectDto projectDto = new ProjectDto();
//    projectDto.setId(1);
//    projectDto.setName("name");
//    projectDto.setDescription("description");
//    projectDto.setDateStart(LocalDate.now());
//    projectDto.setDateEnd(LocalDate.now());
//    when(projectConverter.toProjectDto(any(Project.class))).thenReturn(projectDto);
//
//    final ProjectDto result = projectService.findById(1);
//
//    assertEquals(expected, result);
//  }
//
//  @Test
//  void updateUserPositiveTest() {
//    ProjectDto expected = new ProjectDto();
//    expected.setId(1);
//    expected.setName("name");
//    expected.setDescription("new description");
//    expected.setDateStart(LocalDate.now());
//    expected.setDateEnd(LocalDate.now());
//
//    ProjectDto projectDtoToUpdate = new ProjectDto();
//    projectDtoToUpdate.setDescription("new description");
//
//    Project project = new Project();
//    project.setId(1);
//    project.setName("name");
//    project.setDescription("description");
//    project.setDateStart(LocalDate.now());
//    project.setDateEnd(LocalDate.now());
//    project.setCreatedAt(LocalDate.now());
//    project.setUpdatedAt(LocalDate.now());
//
//    Optional<Project> projectOptional = Optional.of(project);
//    when(projectRepository.findById(1)).thenReturn(projectOptional);
//
//    Project project1 = new Project();
//    project1.setName("name");
//    project1.setDescription("description");
//    project1.setDateStart(LocalDate.now());
//    project1.setDateEnd(LocalDate.now());
//    project1.setCreatedAt(LocalDate.now());
//    project1.setUpdatedAt(LocalDate.now());
//    when(projectConverter.toEntity(any(ProjectDto.class))).thenReturn(project1);
//
//    ProjectDto projectDto = new ProjectDto();
//    projectDto.setId(1);
//    projectDto.setName("name");
//    projectDto.setDescription("new description");
//    projectDto.setDateStart(LocalDate.now());
//    projectDto.setDateEnd(LocalDate.now());
//    when(projectConverter.toUpdatedProjectDto(eq(projectDtoToUpdate), any(Project.class)))
//        .thenReturn(projectDto);
//    when(projectConverter.toProjectDto(any(Project.class))).thenReturn(projectDto);
//
//    Project project2 = new Project();
//    project2.setName("name");
//    project2.setDescription("new description");
//    project2.setDateStart(LocalDate.now());
//    project2.setDateEnd(LocalDate.now());
//    project2.setCreatedAt(LocalDate.now());
//    project2.setUpdatedAt(LocalDate.now());
//    when(projectRepository.save(any(Project.class))).thenReturn(project2);
//
//    final ProjectDto result = projectService.update(1, projectDtoToUpdate);
//
//    assertEquals(expected, result);
//  }
//
//  @Test
//  void deletePositiveTest() {
//    projectService.delete(1);
//
//    verify(projectRepository).deleteById(1);
//  }
//
//  @Test
//  void testAddUserToProject() {
//    ProjectDto expected = new ProjectDto();
//    expected.setId(1);
//    expected.setName("name");
//    expected.setDescription("description");
//    expected.setDateStart(LocalDate.of(2022, 2, 1));
//    expected.setDateEnd(LocalDate.of(2022, 2, 1));
//    expected.setUsersDto(
//        List.of(new UserResponse("email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true)));
//
//    User user = new User();
//    user.setId(1);
//    user.setFirstName("Ivan");
//    user.setLastName("Ivanov");
//    user.setEmail("email@mail.ru");
//    user.setPassword("password");
//    user.setActive(true);
//    user.setRole(Role.ADMIN);
//    user.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
//    UserResponse userDto =
//        new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);
//    when(userConverter.toEntity(
//        userDto))
//        .thenReturn(user);
//    when(userService.findById(1)).thenReturn(userDto);
//
//    User user1 = new User();
//    user1.setId(1);
//    user1.setFirstName("Ivan");
//    user1.setLastName("Ivanov");
//    user1.setEmail("email@mail.ru");
//    user1.setPassword("password");
//    user1.setActive(true);
//    user1.setRole(Role.ADMIN);
//    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
//    Optional<Project> projectOptional =
//        Optional.of(
//            new Project(
//                1,
//                "name",
//                "description",
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                0,
//                0,
//                new ArrayList<>()));
//    projectOptional.get().addEmployee(user1);
//    when(projectRepository.findById(1)).thenReturn(projectOptional);
//
//    User user2 = new User();
//    user2.setId(1);
//    user2.setFirstName("Ivan");
//    user2.setLastName("Ivanov");
//    user2.setEmail("email@mail.ru");
//    user2.setPassword("password");
//    user2.setActive(true);
//    user2.setRole(Role.ADMIN);
//    user2.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user2.setUpdatedAt(LocalDate.of(2022, 2, 1));
//
//    Project project =
//        new Project(
//            1,
//            "name",
//            "description",
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            0,
//            0,
//            new ArrayList<>());
//    project.addEmployee(user2);
//    when(projectRepository.save(any(Project.class))).thenReturn(project);
//
//    ProjectDto projectDto = new ProjectDto();
//    projectDto.setId(1);
//    projectDto.setName("name");
//    projectDto.setDescription("description");
//    projectDto.setDateStart(LocalDate.of(2022, 2, 1));
//    projectDto.setDateEnd(LocalDate.of(2022, 2, 1));
//    List<UserResponse> usersDto = new ArrayList<>();
//    usersDto.add(userDto);
//    projectDto.setUsersDto(usersDto);
//    when(projectConverter.toProjectDto(any(Project.class))).thenReturn(projectDto);
//    ProjectDto result = projectService.addUserToProject(1, 1);
//
//    assertEquals(expected, result);
//    verify(projectRepository).save(any(Project.class));
//  }
//
//  @Test
//  void testDeleteUserFromProject() {
//    User user = new User();
//    user.setId(1);
//    user.setFirstName("Ivan");
//    user.setLastName("Ivanov");
//    user.setEmail("email@mail.ru");
//    user.setPassword("password");
//    user.setActive(true);
//    user.setRole(Role.ADMIN);
//    user.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user.setUpdatedAt(LocalDate.of(2022, 2, 1));
//    UserResponse userDto =
//        new UserResponse(1, "email@mail.ru", "password", "Ivan", "Ivanov", Role.ADMIN, true);
//    when(userConverter.toEntity(
//        userDto))
//        .thenReturn(user);
//
//    when(userService.findById(1)).thenReturn(userDto);
//
//    User user1 = new User();
//    user1.setId(1);
//    user1.setFirstName("Ivan");
//    user1.setLastName("Ivanov");
//    user1.setEmail("email@mail.ru");
//    user1.setPassword("password");
//    user1.setActive(true);
//    user1.setRole(Role.ADMIN);
//    user1.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user1.setUpdatedAt(LocalDate.of(2022, 2, 1));
//    Optional<Project> projectOptional =
//        Optional.of(
//            new Project(
//                1,
//                "name",
//                "description",
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                LocalDate.of(2022, 2, 1),
//                0,
//                0,
//                new ArrayList<>()));
//    projectOptional.get().addEmployee(user1);
//    when(projectRepository.findById(1)).thenReturn(projectOptional);
//
//    User user2 = new User();
//    user2.setId(1);
//    user2.setFirstName("Ivan");
//    user2.setLastName("Ivanov");
//    user2.setEmail("email@mail.ru");
//    user2.setPassword("password");
//    user2.setActive(true);
//    user2.setRole(Role.ADMIN);
//    user2.setCreatedAt(LocalDate.of(2022, 2, 1));
//    user2.setUpdatedAt(LocalDate.of(2022, 2, 1));
//
//    Project project =
//        new Project(
//            1,
//            "name",
//            "description",
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            LocalDate.of(2022, 2, 1),
//            0,
//            0,
//            new ArrayList<>());
//    project.addEmployee(user2);
//    project.addEmployee(user2);
//    when(projectRepository.save(any(Project.class))).thenReturn(project);
//
//    projectService.deleteUserFromProject(1,1);
//
//    verify(projectRepository).save(any(Project.class));
//  }
//
//}