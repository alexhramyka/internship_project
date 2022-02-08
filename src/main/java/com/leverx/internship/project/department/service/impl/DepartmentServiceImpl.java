package com.leverx.internship.project.department.service.impl;

import com.leverx.internship.project.department.repository.DepartmentRepository;
import com.leverx.internship.project.department.repository.entity.Department;
import com.leverx.internship.project.department.service.DepartmentService;
import com.leverx.internship.project.department.service.filter.DepartmentSpecification;
import com.leverx.internship.project.department.web.converter.DepartmentConverter;
import com.leverx.internship.project.department.web.dto.request.DepartmentBodyRequest;
import com.leverx.internship.project.department.web.dto.response.DepartmentProjectsResponse;
import com.leverx.internship.project.department.web.dto.response.DepartmentResponse;
import com.leverx.internship.project.department.web.dto.request.DepartmentParamRequest;
import com.leverx.internship.project.department.web.dto.response.DepartmentUsersResponse;
import com.leverx.internship.project.project.service.ProjectService;
import com.leverx.internship.project.project.web.dto.response.ProjectResponse;
import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepository departmentRepository;
  private final DepartmentConverter departmentConverter;
  private final UserService userService;
  private final ProjectService projectService;

  @Override
  @Transactional(readOnly = true)
  public List<DepartmentResponse> findAll(int page, int size, DepartmentParamRequest departmentParamRequest) {
    Specification<Department> spec = Specification.where(
        DepartmentSpecification.departmentParamHasDescription(departmentParamRequest.getDescription()).and(
        DepartmentSpecification.departmentParamHasName(departmentParamRequest.getName())));
    Pageable paging = PageRequest.of(page, size);
    Page<Department> departmentPage = departmentRepository.findAll(spec, paging);
    List<DepartmentResponse> departmentsDto = new ArrayList<>();
    departmentPage.forEach(
        department -> departmentsDto.add(departmentConverter.toDepartmentResponse(department)));
    return departmentsDto;
  }

  @Override
  @Transactional(readOnly = true)
  public DepartmentResponse findById(Integer id) {
    return departmentConverter.toDepartmentResponse(getDepartment(id));
  }

  @Override
  @Transactional
  public DepartmentResponse create(DepartmentBodyRequest departmentBodyRequest) {
    Department department = departmentConverter.toEntity(departmentBodyRequest);
    department.setCreatedAt(LocalDate.now());
    department.setUpdatedAt(LocalDate.now());
    return departmentConverter.toDepartmentResponse(departmentRepository.save(department));
  }

  @Override
  @Transactional
  public DepartmentResponse update(Integer id, DepartmentBodyRequest departmentBodyRequestToUpdate) {
    DepartmentResponse newDepartmentResponse = departmentConverter
        .toUpdatedDepartmentDto(departmentBodyRequestToUpdate, getDepartment(id));
    Department newDepartment = departmentConverter.toEntity(newDepartmentResponse);
    newDepartment.setUpdatedAt(LocalDate.now());
    newDepartment.setId(id);
    return departmentConverter.toDepartmentResponse(departmentRepository.save(newDepartment));
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    getDepartment(id);
    departmentRepository.deleteById(id);
  }

  @Override
  @Transactional
  public DepartmentUsersResponse addEmployeeToDepartment(Integer idDepartment, Integer idUser) {
    DepartmentUsersResponse departmentUsersResponse = departmentConverter
        .toDepartmentsUsersResponse(getDepartment(idDepartment));
    UserResponse user = userService.findById(idUser);
    if (!departmentUsersResponse.getEmployeesDto().contains(user)) {
      departmentUsersResponse.addUser(user);
      Department department = departmentConverter
          .toUpdatedEntity(departmentUsersResponse, getDepartment(idDepartment));
      return departmentConverter.toDepartmentsUsersResponse(
          departmentRepository.save(department));
    } else throw new DataIntegrityViolationException("User with id " + user.getId() + " already in this department");
  }

  @Override
  @Transactional
  public DepartmentProjectsResponse addProjectToDepartment(Integer idDepartment, Integer idProject) {
    DepartmentProjectsResponse departmentProjectsResponse = departmentConverter
        .toDepartmentProjectsResponse(getDepartment(idDepartment));
    ProjectResponse project = projectService.findById(idProject);
    if (!departmentProjectsResponse.getProjectsDto().contains(project)) {
      departmentProjectsResponse.addProject(project);
      Department department = departmentConverter
          .toUpdatedEntity(departmentProjectsResponse, getDepartment(idDepartment));
      return departmentConverter.toDepartmentProjectsResponse(
          departmentRepository.save(department));
    } else throw new DataIntegrityViolationException("Project with id " + project.getId() + " already in this department");
  }

  @Override
  @Transactional
  public void removeProjectFromDepartment(int idDepartment, int idProject) {
    DepartmentProjectsResponse departmentProjectsResponse = departmentConverter
        .toDepartmentProjectsResponse(getDepartment(idDepartment));
    ProjectResponse project = projectService.findById(idProject);
    if (departmentProjectsResponse.getProjectsDto().contains(project)) {
      departmentProjectsResponse.removeProject(project);
      Department department = departmentConverter
          .toUpdatedEntity(departmentProjectsResponse, getDepartment(idDepartment));
      departmentRepository.save(department);
    } else throw new NotFoundException("Project with id " + idProject + " doesn't  exists in department with id " + idDepartment);
  }

  private Department getDepartment(int id) {
    return departmentRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Department with id: " + id + " doesn't exist"));
  }
}
