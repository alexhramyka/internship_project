package com.leverx.internship.project.department.web.controller;

import com.leverx.internship.project.config.H2ConfigTest;
import com.leverx.internship.project.config.SpringConfig;
import com.leverx.internship.project.config.WebConfig;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, H2ConfigTest.class})
@WebAppConfiguration(value = "src/main/java/com/leverx/internship/project")
@Sql(value = "classpath:data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:delete_data-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Scope(value = "test")
public class DepartmentControllerTest {

  private final WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Autowired
  public DepartmentControllerTest(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void getAllDepartmentsTestPositive() throws Exception {
    MockHttpServletResponse response = mockMvc
        .perform(
            get("/departments")
                .param("size", "3")
                .param("page", "0")
                .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void findDepartmentByIdTestPositive() throws Exception {
    MockHttpServletResponse response = mockMvc
        .perform(
            get("/departments/{id}", 2)
                .param("size", "3")
                .param("page", "0")
                .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void createTestPositive() throws Exception {
    String jsonString =
        new JSONObject()
            .put("name", "name1")
            .put("description", "description")
            .toString();
    MockHttpServletResponse response = mockMvc
        .perform(
            post("/departments")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
  }

  @Test
  void updateTestPositive() throws Exception {
    String jsonString =
        new JSONObject()
            .put("description", "new description").toString();
    final MockHttpServletResponse response = mockMvc.perform(put("/departments/{id}", 2)
            .content(jsonString).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void deleteTestPositive() throws Exception {
    final MockHttpServletResponse response = mockMvc.perform(delete("/departments/{id}", 4)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus()) ;
  }

  @Test
  void addUserToDepartmentTestPositive() throws Exception {
    final MockHttpServletResponse response =
        mockMvc
            .perform(
                put("/departments/{idDep}/users/{idUser}", 4, 2)
                    .accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void addProjectToDepartmentTestPositive() throws Exception {
    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/departments/{idDep}/projects/{idProj}", 4, 2)
                    .accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void deleteProjectFromDepartmentTestPositive() throws Exception {
    final MockHttpServletResponse response = mockMvc.perform(delete("/departments/{idDep}/projects/{idProj}", 3, 4)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    System.out.println(response.getContentAsString());

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }
}
