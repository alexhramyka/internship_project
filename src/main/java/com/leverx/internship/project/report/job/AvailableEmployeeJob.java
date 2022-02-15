package com.leverx.internship.project.report.job;

import com.leverx.internship.project.department.repository.DepartmentRepository;
import com.leverx.internship.project.department.repository.entity.Department;
import com.leverx.internship.project.department.web.converter.DepartmentConverter;
import com.leverx.internship.project.department.web.dto.response.DepartmentResponse;
import com.leverx.internship.project.exception.ReportNotFoundException;
import com.leverx.internship.project.project.repository.ProjectRepository;
import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.project.web.converter.ProjectConverter;
import com.leverx.internship.project.project.web.dto.response.ProjectResponse;
import com.leverx.internship.project.report.model.ReportType;
import com.leverx.internship.project.report.excel.generator.ExcelWorkbookGenerator;
import com.leverx.internship.project.user.repository.UserRepository;
import com.leverx.internship.project.user.web.converter.UserConverter;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PropertySource("classpath:application.properties")
public class AvailableEmployeeJob {
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final ProjectConverter projectConverter;
  private final ReportType reportType = ReportType.AvailableEmployeeReport;
  private final ExcelWorkbookGenerator excelWorkbookGenerator;
  private final DepartmentRepository departmentRepository;
  private final DepartmentConverter departmentConverter;
  private final static String fileName = "%sAvailable_employee-%s.xlsx";
  @Value("${output.file.path}")
  private String pathForOutput;

  @Autowired
  public AvailableEmployeeJob(
      ProjectRepository projectRepository,
      UserRepository userRepository,
      UserConverter userConverter,
      ProjectConverter projectConverter,
      ExcelWorkbookGenerator excelWorkbookGenerator,
      DepartmentRepository departmentRepository,
      DepartmentConverter departmentConverter) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
    this.userConverter = userConverter;
    this.projectConverter = projectConverter;
    this.excelWorkbookGenerator = excelWorkbookGenerator;
    this.departmentRepository = departmentRepository;
    this.departmentConverter = departmentConverter;
  }

  @Transactional(readOnly = true)
  @Scheduled(cron = "${cron.expression}")
  public void createAvailableEmployeeReport() {
    File file =
        new File(String.format(fileName, pathForOutput, LocalDate.now().getMonth().name()));
    try (FileOutputStream fos = new FileOutputStream(file)) {
      Workbook workbook = excelWorkbookGenerator.generateReportWorkbook(reportType, getAvailableEmployee());
      workbook.write(fos);
    } catch (IOException e) {
      throw new ReportNotFoundException(e.getMessage());
    }
  }

  @Transactional(readOnly = true)
  public Map<DepartmentResponse, Map<UserResponse, Set<ProjectResponse>>> getAvailableEmployee() {
    List<Department> departments = departmentRepository.findAll();
    Map<DepartmentResponse, Map<UserResponse, Set<ProjectResponse>>> reportResult = new HashMap<>();
    LocalDate nextMonth = LocalDate.now().plusDays(30);
    int limitOfProjects = 3;
    departments.forEach(
        department -> {
    Map<UserResponse, Set<ProjectResponse>> availableEmployees = new HashMap<>();
    userRepository
        .findAll()
        .forEach(
            employee -> {
              Set<Project> projects = new HashSet<>(
                  projectRepository.findProjectsByEmployeesIdAndDateEndAfter(
                      employee.getId(), nextMonth));
              if (projects.size() < limitOfProjects) {
                availableEmployees.put(
                    userConverter.toUserResponse(employee),
                    projectConverter.projectSetToProjectRespSet(projects));
              }
            });
          reportResult.put(departmentConverter.toDepartmentResponse(department), availableEmployees);
        });
    return reportResult;
  }
}
